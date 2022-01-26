# IES Project

Project Abstract:
- Title: beStrong
- Description: the application concept for this project is a managing application for a gym. The main goals are, for example:
    - managing entries
    - listing available classes
    - chat with PT’s and also
    - make payments.

Project Team:
- 97484: Pedro Monteiro - DevOps
- 98134: Tiago Matos - Architect
- 98396: Vítor Dias - Product Owner
- 98512: Eduardo Fernandes - Team Manager

Resources:
- Board: https://be-strong.atlassian.net/jira/software/projects/BES/boards/1
- Report: https://github.com/pedromonteiro01/beStrong/blob/main/reports/report.pdf

## How to run locally
First you need to run maven commands to generate the jar. the first command to run is to enter the right directory
```
cd beStrong_server/
```
And then delete the last jar:
```
mvn clean
```
Finally, this one will generate a new one
```
mvn package
```
After that you just have to go back to the previous directory
```
cd ..
```
And run the script that will start dockers
```
./run.sh
```
The project will be accessible at [172.18.0.9:8081](http://172.18.0.9:8081)

## API Documentation

## Accesses

| *Email* | *Password* | *Role*    |
|------------|------------|-----------|
| candreuzzi3@howstuffworks.com   | JeqHeCGK9WQs       | Trainer    |
| wimpettp@g.co      | 1MgLuwygcpb       | Client   |
