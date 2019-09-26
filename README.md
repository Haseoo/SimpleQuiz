# SimpleQuiz Java
![Project logo](https://raw.githubusercontent.com/Haseoo/SimpleQuizJava/master/src/main/resources/windows/logo.png "Project logo")

SimpleQuizJava is a game project based on my [first-year-study project](https://github.com/Haseoo/SimpleQuizC  "first-year-study project") written in C. I was making this project to learn Java in practice. I believed this would be a good idea to make a project with the concept I already know.  I finished the game during the semester but I didn't push the code to GitHub. I didn't like the spaghetti code I had written. During the summer internship, I learned some good coding practices and I decided to rewrite entire code. I'm not sure that my name convention is good or not but I haven't came up with a better one.

## Build and run
The game is run with the command:
-   `gradlew.bat run` for Windows
-   `gradlew run`  for Linux i MacOS

You can also build the .jar file by the command:
-   `gradlew.bat jar` for Windows
-   `gradlew jar`  for Linux i MacOS

## Assumptions
- The game, as the C-written one, has two game modes: With and without choosing the question category.
- The player can specify if the player falls out after the wrong answer.
- The player can specify the number of rounds.
- The player can resign.
- The questions are single-choice.
- The questions can't repeat in one play unless they are repeated in the repository.
- The questions are loaded from text files.

## Technologies
The whole project is written in Java with high usage of Stream API. The graphical UI is written using open JavaFX. At some point, I was using Spring but I quickly realised that was excess of form over substance.

## Question repository
The questions are stored in .json files. The questions originally were stored in XML files. But at one point I decided to switch XML to more user-friendly JSON format. To play the game you need to load the question categories list file. The file contains category names and relative paths to files with questions. You can do it by selecing `Game->Load repository` The file structure is:
```
[
    {
        "name": "category name",
        "jsonFilePath" : "question file relative path"
    },
	...
]
```
The .json file with questions structure is:
```
[
  {
    "content": "question text",
    "answers": [
      "answer",
     ...
    ],
    "correctAnswerIndex": correct answer index starting from zero
  },
  ...
]
```

## The future
I planned to make an online client-server version of this game using only Java but I gave up this idea. Now I'm planning to make the web version of this game (yeah, yet another version) in Java EE. Simply because to learn this platform.
