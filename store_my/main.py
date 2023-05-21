import robot

logFile = open('mylog.txt', 'w')
robot.run("TC_login.robot",stdout=logFile)