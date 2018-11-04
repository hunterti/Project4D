import csv
import random
def generate2DMap():
    allOfTheLines = []
    ticker = 3
    otherTicker = 0
    with open("/Users/Acer/PycharmProjects/Hackathon2018/pi-million.txt",'r') as f:
        for line in f:
            for i in range(0,2500):
                allOfTheLines = []
                for j in range(1,50): #J is my x of wall
                    for h in range(1,50): #H is my y of wall
                        allOfTheLines.append([j,h,line[ticker],random.randint(0,10),random.randint(0,10)])
                        ticker = ticker + 1
                        if ticker > 1000000:
                            ticker = 0
                for j in range(0,1): # X from 0 to 1
                    for h in range(0,51):
                        allOfTheLines.append([j,h,"12",random.randint(0,10),random.randint(0,10)])
                for j in range(50,51):
                    for h in range(0,51):
                        allOfTheLines.append([j,h,"11",random.randint(0,10),random.randint(0,10)])
                for j in range(0,51):
                    for h in range(50,51):
                        allOfTheLines.append([j,h,"14",random.randint(0,10),random.randint(0,10)])
                for j in range(0,51):
                    for h in range(0,1):
                        allOfTheLines.append([j,h,"13",random.randint(0,10),random.randint(0,10)])
                with open("/Users/Acer/PycharmProjects/Hackathon2018/" + str(i) + ".txt", 'w') as w:
                    writer = csv.writer(w)
                    writer.writerows(allOfTheLines)





generate2DMap()