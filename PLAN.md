# Project Plan

## Project Structure
* [BookManager](https://github.com/qThegamEp/BookManager)
    * [src](src)
        * [main](src/main)
            * [java](src/main/java)
                * [com](src/main/java/com)
                    * [qthegamep](src/main/java/com/qthegamep)
                        * [bookmanager](src/main/java/com/qthegamep/bookmanager)
                            * [model](src/main/java/com/qthegamep/bookmanager/model)
                                * [entity](src/main/java/com/qthegamep/bookmanager/model/entity)
                                    * [Book.java](src/main/java/com/qthegamep/bookmanager/model/entity/Book.java)
                            * [Application.java](src/main/java/com/qthegamep/bookmanager/Application.java)
            * [resources](src/main/resources)
                * [db](src/main/resources/db)
                    * [initDB_MySQL.sql](src/main/resources/db/initDB_MySQL.sql)
                    * [populateDB_MySQL.sql](src/main/resources/db/populateDB_MySQL.sql)
                * [logback.xml](src/main/resources/logback.xml)
        * [test](src/test)
            * [java](src/test/java)
                * [com](src/test/java/com)
                    * [qthegamep](src/test/java/com/qthegamep)
                        * [bookmanager](src/test/java/com/qthegamep/bookmanager)
                            * [model](src/test/java/com/qthegamep/bookmanager/model)
                                * [entity](src/test/java/com/qthegamep/bookmanager/model/entity)
                                    * [BookTest.java](src/test/java/com/qthegamep/bookmanager/model/entity/BookTest.java)
                            * [test](src/test/java/com/qthegamep/bookmanager/test)
                                * [rule](src/test/java/com/qthegamep/bookmanager/test/rule)
                                    * [Rules.java](src/test/java/com/qthegamep/bookmanager/test/rule/Rules.java)
                                * [util](src/test/java/com/qthegamep/bookmanager/test/util)
                                    * [TestUtil.java](src/test/java/com/qthegamep/bookmanager/test/util/TestUtil.java)
                            * [ApplicationTest.java](src/test/java/com/qthegamep/bookmanager/ApplicationTest.java)
            * [resources](src/test/resources)
                * [logback-test.xml](src/test/resources/logback-test.xml)
    * [.appveyor.yml](.appveyor.yml)
    * [.gitignore](.gitignore)
    * [.scrutinizer.yml](.scrutinizer.yml)
    * [.travis.yml](.travis.yml)
    * [LICENSE](LICENSE)
    * [PLAN.md](PLAN.md)
    * [pom.xml](pom.xml)
    * [README.md](README.md)

## Fix In Future
* Scrutinizer don't work

## Bugs && Messages
* Book.class class modifier message
