install.packages("lattice")
library(lattice)

data <- read.csv("data_male.csv",header = TRUE)
x <- data[,1:83*2-1]
y <- data[,1:83*2]

#contour####
#以contour_left1为坐标原点，脸部最宽为单位长度（以处理好）,主要用来区分长脸和短脸
contour = data[,1:38]
contour.x = x[,1:19]
contour.y = y[,1:19]
setwd("F:/study/SRTP/模型训练/classified/男/contour/散点图")
for(i in 1:28){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour.x[i,]),c(contour.y[i,]),pch=20,ylim = c(-0.1,0.9),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/data plot/男")
contour.d = dist(contour)
contour.hc = hclust(contour.d)
plot(contour.hc,hang = -1)
rect.hclust(contour.hc, k=2, border="red")

plot(c(contour.x[10,]),c(contour.y[10,]),pch=20,col="green")
points(c(contour.x[11,]),c(contour.y[11,]),pch=20,col="red")
points(c(contour.x[5,]),c(contour.y[5,]),pch=20,col="blue")
points(c(contour.x[6,]),c(contour.y[6,]),pch=20,col="yellow")
points(c(contour.x[2,]),c(contour.y[2,]),pch=20,col="black")
points(c(contour.x[4,]),c(contour.y[4,]),pch=20,col="cyan")

write.csv(contour,"contour.csv")

#contour1####
#同时设定y方向单位长度，以每一张脸部最长为单位y方向长度，使脸一样长，主要用来区分宽脸和瘦脸
contour1 = contour
for(i in 1:19*2){
  contour1[,i] = contour[,i]/contour[,2]
}
contour1.x = contour1[,1:19*2-1]
contour1.y = contour1[,1:19*2]
setwd("F:/study/SRTP/模型训练/classified/男/contour1/散点图")
for(i in 1:28){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour1.x[i,]),c(contour1.y[i,]),pch=20,ylim = c(0,1),xlab = "x",ylab = "y",main = i)
  dev.off()
}

setwd("F:/study/SRTP/模型训练/data plot/男")
contour1.d = dist(contour1)
contour1.hc = hclust(contour1.d)
plot(contour1.hc,hang = -1)
rect.hclust(contour1.hc, k=2, border="red")

plot(c(contour1.x[11,]),c(contour1.y[11,]),pch=20,col="green")
points(c(contour1.x[18,]),c(contour1.y[18,]),pch=20,col="red")
points(c(contour1.x[9,]),c(contour1.y[9,]),pch=20,col="black")

write.csv(contour1,"contour1.csv")
