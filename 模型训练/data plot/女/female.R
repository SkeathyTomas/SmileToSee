install.packages("lattice")
library(lattice)

data <- read.csv("data_female.csv",header = TRUE)
x <- data[,1:83*2-1]
y <- data[,1:83*2]

#contour####
#第一种标准化方法，每一张脸的脸部最宽为单位长度（横向x方向总长固定），可以对脸长的不同进行较好的聚类####
contour = data[,1:38]
contour.x = x[,1:19]
contour.y = y[,1:19]
#输出散点图
setwd("F:/study/SRTP/模型训练/classified/女/contour/散点图")
for(i in 1:34){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour.x[i,]),c(contour.y[i,]),pch=20,ylim = c(-0.1,0.9),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/dada plot/女")
#聚类分析
contour.d = dist(contour)
contour.hc = hclust(contour.d)
plot(contour.hc,hang = -1)
rect.hclust(contour.hc, k=2, border="red")
contour.group = cutree(contour.hc,k=2)

#写入分类
for(i in 1:34){
  if(contour.group[i]==1) contour$class[i]="a";
  if(contour.group[i]==2) contour$class[i]="b";
}

plot(c(contour.x[26,]),c(contour.y[26,]),pch=20,col="green")
points(c(contour.x[7,]),c(contour.y[7,]),pch=20,col="red")
points(c(contour.x[8,]),c(contour.y[8,]),pch=20,col="blue")
points(c(contour.x[1,]),c(contour.y[1,]),pch=20,col="yellow")
points(c(contour.x[2,]),c(contour.y[2,]),pch=20,col="black")

setwd("F:/study/SRTP/模型训练/classified/女/contour/")
write.csv(contour,"contour.csv",row.names = FALSE)

#第二种标准化方法，横向纵向分别设置不同的单位长度（x,y方向总长固定），可对脸的胖瘦有较好的区分####
contour1 = contour
for(i in 1:19*2){
  contour1[,i] = contour[,i]/contour[,2]
}
contour1.x = contour1[,1:19*2-1]
contour1.y = contour1[,1:19*2]
setwd("F:/study/SRTP/模型训练/classified/女/contour1/散点图")
for(i in 1:34){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour1.x[i,]),c(contour1.y[i,]),pch=20,ylim = c(0,1),xlab = "x",ylab = "y",main = i)
  dev.off()
}

contour1.d = dist(contour1)
contour1.hc = hclust(contour1.d)
plot(contour1.hc,hang = -1)
rect.hclust(contour1.hc, k=2, border="red")
contour1.group = cutree(contour1.hc,k=2)

plot(c(contour1.x[11,]),c(contour1.y[11,]),pch=20,col="green")
points(c(contour1.x[5,]),c(contour1.y[5,]),pch=20,col="red")
points(c(contour1.x[12,]),c(contour1.y[12,]),pch=20,col="black")

setwd("F:/study/SRTP/模型训练/classified/女/contour1")
#写入分类
for(i in 1:34){
  if(contour1.group[i]==1) contour1$class[i]="a";
  if(contour1.group[i]==2) contour1$class[i]="b";
}
write.csv(contour1,"contour1.csv",row.names = FALSE)

setwd("F:/study/SRTP/模型训练/data plot/女")
