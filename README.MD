# Advent of Code 2017

This project compiles all efforts done to complete 2017's Advent of code as seen at [http://adventofcode.com/2017] .

The motivation to tackle these exercises is to play/study several different technology elements like different coding languages and frameworks.

## Building/Running

The whole of this project uses [Gradle](https://gradle.org/) using a [multi-project build approach](https://docs.gradle.org/current/userguide/multi_project_builds.html).
To facilitate setup, it includes the use of the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

### Listing all projects

From the root of the project, run
```
./gradlew projects
```

### Listing tasks for one the sub-projects

From the root of the project, run
```
./gradlew <project_name>:tasks
```

For example, for _aoc01_, run
```
./gradlew aoc01:tasks
```

### Running any of a sub-project's subtasks

From the root of the project, run
```
./gradlew <project_name>:<task_name>
```

For example, to run tests for _aoc01_, run
```
./gradlew aoc01:test
```

### Available sub-projects

- Advent of Code - Day 01: Inverse Captcha
  - Project name: aoc01
  - [README.MD](aoc01/README.MD)
  - Tehcnology studied during this exercise:
    - [JUnit5](http://junit.org/junit5/)
    - [JCommander](http://www.jcommander.org/)