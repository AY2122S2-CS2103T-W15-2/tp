---
layout: page
title: Jian Rong's Project Portfolio Page
---

### Project: HustleBook

HustleBook is a desktop app specially catered towards financial advisors for **managing client details** and **scheduling meetings efficiently** without the need to lift their hands off the keyboard.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

This project is built on top of the **AB3** application that is used for teaching Software Engineering principles.


Given below are my contributions to the project.

* **New Feature**: Added the ability for users to specify the specific client they wish to perform an action on
  in the case of their HustleBook containing clients with similar names.
    * What it does: If a client has 2 clients, named `David Li` and `David Tan`, the command edit David will firstly show both those Davids,
      and will ask the user again to clarify which "David" they refer to
    * Justification: This feature enhances existing features as users do not have to type the full name of the clients they wish to perform actions on.
    * Highlights: This enhancement currently affects `edit`, `delete`, `find` and `meet`. It will affect other features that use name to identify clients in the future
  

* **New Feature**: Added the ability for users to see their previous commands by pressing the "up arrow" when their cursor is in the textbox.
    * What it does: A client can see their previously inputted command by pressing the "up arrow".
    * Justification: Some commands can be very long, and it is easier for users if we kept track of previous commands and displayed it for them.
    * Highlights: This enhancement affects all commands that the user inputs.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kuanjr&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=j4ck990&tabRepo=AY2122S2-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~docs~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
    * Improved the way HustleBook searches for users with a given search string.
    * Added Number Parser to assist in situations with users with similar names
    * Wrote additional tests for existing features to increase coverage from 60.11% to 64.50% (Pull request [\#254](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/254))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit` and `delete`[\#257](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/257)
        * Did cosmetic tweaks to existing documentation of features `edit`, `delete`: [\#130](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/130/)
    * Developer Guide:
        * Added implementation details of the way HustleBook searches for clients by name

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/kuanjr/ped/issues/12), [2](https://github.com/kuanjr/ped/issues/11), [3](https://github.com/kuanjr/ped/issues/10))


