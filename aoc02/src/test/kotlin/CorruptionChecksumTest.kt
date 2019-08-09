package net.aoc

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CorruptionChecksumTest {

    val corruptionChecksum = CorruptionChecksum()

    @MockK
    lateinit var mockChecksumOperation: (List<Long>) -> Long

    @BeforeEach
    fun setUp() {
        mockkObject(RowChecksumOperation.Factory)
        every { mockChecksumOperation(any()) } returns 1L
        every { RowChecksumOperation.create(any()) } returns mockChecksumOperation
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    private fun createRowString(vararg rowNumbers: Long) = rowNumbers.joinToString("\t")

    @Test
    fun givenAnEmptySequenceOfRowsShouldCalculateItsChecksum() {
        Assertions.assertThat(corruptionChecksum.calculateSequenceChecksum(emptySequence())).isEqualTo(0L)

        verify { RowChecksumOperation.create(any()) wasNot Called }
        verify { mockChecksumOperation(any()) wasNot Called}
    }

    @Test
    fun givenAnEmptyLineSequenceOfRowsShouldCalculateItsChecksum() {
        Assertions.assertThat(corruptionChecksum.calculateSequenceChecksum(listOf("").asSequence())).isEqualTo(1L)

        verify { RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.DIFF) }
        verify { mockChecksumOperation(emptyList()) }
    }

    @Test
    fun givenASequenceOfRowsShouldCalculateItsChecksumUsingDefaultRowChecksumOperation() {
        Assertions.assertThat(corruptionChecksum.calculateSequenceChecksum(listOf(createRowString(1)).asSequence())).isEqualTo(1L)

        verify { RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.DIFF) }
        verify { mockChecksumOperation(listOf(1L)) }
    }

    @Test
    fun givenASequenceOfRowsAndRowChecksumMethodShouldCalculateItsChecksumUsingProvidedChecksum() {
        Assertions.assertThat(corruptionChecksum.calculateSequenceChecksum(listOf(createRowString(1)).asSequence(), RowChecksumOperation.CorruptionChecksumType.EVEN_DIV)).isEqualTo(1L)

        verify { RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.EVEN_DIV) }
        verify { mockChecksumOperation(listOf(1L)) }
    }

    @Test
    fun givenASequenceOFMultipleRowsShouldCalculateTheirCompleteChecksum() {
        Assertions.assertThat(corruptionChecksum.calculateSequenceChecksum(listOf(createRowString(1),
                createRowString(1, 2),
                createRowString(1, 2, 3)
                ).asSequence())).isEqualTo(3)

        verify { RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.DIFF)}
        verify {mockChecksumOperation(listOf(1))}
        verify {mockChecksumOperation(listOf(1, 2))}
        verify {mockChecksumOperation(listOf(1, 2, 3))}
    }
}