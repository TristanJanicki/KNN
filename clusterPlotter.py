import matplotlib.pyplot as plt

if __name__ == "__main__":
    
    
    cf = open("clusters.txt", "r")
    centroidFile = open("centroids.txt", "r")
    
    cfd = cf.read()
    
    clusterCoordinates = []
    centroids = []
    
    for line in centroidFile.readlines():
        parts = line.replace("\n", "").split(",")
        centroids.append((float(parts[0]), float(parts[1])))
        
    clusters = cfd.split(";")
    i = 0
    plt.figure(1)
    for c in clusters:
        #print(c)
        cCords = c.split(",")
        #print("Cluster x")
        currClusterX = []
        currClusterY = []
        currClusterCentroid = []

        for cord in cCords:
            cordParts = cord.split(":")
            #print(len(cordParts))
            
            if len(cordParts) == 3:
                i = i + 1
                print(cordParts[1].replace("lng", ""), cordParts[2], ",")
                y = float(cordParts[1].replace("l", "").replace("n", "").replace("g", ""))
                x = float(cordParts[2])
                currClusterX.append(x)
                currClusterY.append(y)
                #print(x,y)
        clusterCoordinates.append((currClusterX, currClusterY))
        
    #print(clusterCoordinates)
    
    si = 0
    shapes = ["ro", "bo", "yo", "go", "co"] #, "mo", "co", "rs", "b.", "yv", "c*", "m+"]
    #cShapes = ["r^", "b^", "y^", "g^", "c^", "m^", "c^", "rs", "b10", "y4", "c9", "m11"]
    
    
    for p,c in zip(clusterCoordinates, centroids):
        if len(c) == 2:
            plt.plot(p[0], p[1], shapes[si])
            #plt.plot(c[0], c[1], cShapes[si])
            si += 1
    
    