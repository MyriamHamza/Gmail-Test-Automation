# Getting started 

The class materials will be shared by 4 different gitlab projects. I will add to the next project every week.

Every week you will have a task that has to be done on that week. All group has same deadline Monday 6:00 AM CET for these weekly tasks. After this deadline I do not want to hear excuses.:) 

Every week I will put a video here as well that contains more details and explanation in word.

Our main communication form will be the Teams chat

Contact me on Teams chat if you have any problem anytime. Feel free.

# Video

This time we have 3 separate videos, watch them after each other.

Introduction: https://www.youtube.com/watch?v=KSJSTe1GvJo

Gitlab 1./1. Gitlab basics: https://www.youtube.com/watch?v=YhZfXpUjgjE

Gitlab 1./2. Continous Integration: https://www.youtube.com/watch?v=916-98g5Y3o

# Gitlab

Using a gitlab repository is easier if we configure at least SSH key authentication for commits.

# Using key file [Optional]

To avoid always giving password and make the connection more secure, we will use key file to authenticate our machine as a commiter.

https://docs.gitlab.com/ee/ssh/

## Linux

```
ssh-keygen -o
cat ~/.ssh/id_rsa.pub
```

## Windows

https://ourcodeworld.com/articles/read/1421/how-to-create-a-ssh-key-to-work-with-github-and-gitlab-using-puttygen-in-windows-10


# Use GPG [Optional]

https://gitlab.inf.elte.hu/help/user/project/repository/gpg_signed_commits/index.md

# Issues/Wiki

https://docs.gitlab.com/ee/user/project/issues/

https://docs.gitlab.com/ee/user/project/wiki/

# Continuous integration

Utilize automated test on server on every commit to make sure the code is stable in the repository.

https://en.wikipedia.org/wiki/Continuous_integration

# Git Branching

Create your own branch in the sandbox gitlab project and you can try out your CI files.

For exmaple:

`git checkout -b my_branch`

[Implement code]

```
git commit -am "#1 add first commit of CI file"
git push origin my_branch
```

Gitlab commit message rules: https://about.gitlab.com/blog/2016/03/08/gitlab-tutorial-its-all-connected/

Commit comment best practices: https://chris.beams.io/posts/git-commit/


# First CI file

```
.gitlab-ci.yml
```

First example (just saying Hello Worlds):
```
something:
    script:
        - echo "Hello World!"
```

# Details on Gitlab and Docker

- What is a gitlab-runner? - https://docs.gitlab.com/runner/
- What is Docker? - https://docs.docker.com/get-started/overview/

# Open the result of the job.

Repository > Branches you can find your branch. If you click the icon next to the "merge request" you can open the result of the last job related to your branch

- What is that test? - Staging
- Stage priority: build -> test -> deploy


# Second CI file (multiple stage)
```
say_hi_for_testers:
    stage: test
    script:
        - echo "Hello World!"

say_hi_for_builders:
    stage: build
    script:
        - echo "Hello World!"
```


Detailed info about GitlabCI: https://docs.gitlab.com/ee/ci/quick_start/index.html

More info on pipelines: https://docs.gitlab.com/ee/ci/pipelines/pipeline_architectures.html

# Linter

You can check your syntax of your Gitlab File.

https://docs.gitlab.com/ee/ci/lint.html

# Unit test

https://github.com/JacksonFurrier/SQAT

## File: `src/main/java/SimpleMultiplier.java`

```
public class SimpleMultiplier{

    SimpleMultiplier(){
    }

    public int multiply(int aLeft, int aRight)
    {
        return aLeft * aRight;
    }
}
```


## File: `src/test/java/MultiplyTest.java`


```
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

public class MultiplyTest {

    SimpleMultiplier multiplier;

    @Before
    public void Setup()
    {
       multiplier = new SimpleMultiplier(); 
    }

    @Test
    public void baseMultiplicationTests() {
        assertEquals(  "10 x 1 must be 10", 10, multiplier.multiply(10, 1) );
        assertEquals(  "0 x 10 must be 0", 0, multiplier.multiply(0, 10) );
        assertEquals( "0 x 0 must be 0", 0, multiplier.multiply(0, 0) );
    }
}
```

## File: `build.gradle`

```
plugins {
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:31.1-jre'
}

testing {
    suites {
        test {
            useJUnit('4.13.2')
        }
    }
}

application {
    mainClass = 'MultiplyTest.App'
}

```

## Test with docker

```
docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project -it gradle bash
```


## File: .gitlab-ci.yml

```
run_unit_test:
    stage: test
    script:
        - gradle test
```


## .gitignore

Add the compiled and temporary folders to ignore file, because that makes the repository heavyer and unnecessary.

```
build
.gradle
```


## Missing Gradle

```
$ gradle test
/usr/bin/bash: line 123: gradle: command not found
```

From this point because we want to use something not only simple bash, we have to define an image that contains the necessary tools.

We can find easily a gradle that contains Java, so we can add an image to use.

```
run_unit_test:
    stage: test
    image: gradle:8.0.2
    script:
        - gradle test
```

## More information about test results

If you need more information why the test was failed, you can run with `--info` or `--debug` parameter the test. First use the `--info`

For example:
```
gradle test --info
```

# Task

Our goal is to create a class that can calculate the max of two values. 
The function returns the bigger value of two values.

But we are pro developer, so we do in a TDD way.

- Create an issue assigned to yourself that indicates, you have to do the test cases 
- Create an issue assigned to yourself about implementing the code.
- Implement the test class and commit.
- Implement the code and commit.
- Run your code test locally to see if it is correctly running.
- Use .gitignore to ignore all non-necessary file to run the tests)
- Create a CI file and check if the test runs correctly on server.
- Create a merge request to the master and assign that to me @hudi89.

# Extra task

Get free IntelliJ licence and run your gradle project in the IDE.
https://www.jetbrains.com/community/education/#students
