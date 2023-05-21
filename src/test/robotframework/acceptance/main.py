import robot

if __name__ == "__main__":
logFile = open('mylog.txt', 'w')
robot.run("TC_login.robot", stdout=logFile)
