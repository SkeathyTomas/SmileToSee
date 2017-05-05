install.packages("lattice")
library(lattice)

data <- read.csv("data_all 2.0.csv",header = TRUE)
x <- data[,1:83*2-1]
y <- data[,1:83*2]

#contour已标准化过,区分脸的长短####
contour = data[,1:38]
contour.x = contour[,1:19*2-1]
contour.y = contour[,1:19*2]
#图片输出
setwd("F:/study/SRTP/模型训练/classified/全/contour/散点图")
for(i in 1:62){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour.x[i,]),c(contour.y[i,]),pch=20,xlim = c(0,1),ylim = c(0,1),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/data plot/全")
#聚类分析
contour.d = dist(contour)
contour.hc = hclust(contour.d)
plot(contour.hc,hang = -1)
rect.hclust(contour.hc, k=3, border="red")
#输出类信息
contour.group = cutree(contour.hc,k=3)
for(i in 1:62){
  if(contour.group[i]==1) contour$class[i]= 1;
  if(contour.group[i]==2) contour$class[i]= 2;
  if(contour.group[i]==3) contour$class[i]= 3;
}
#输出csv
setwd("F:/study/SRTP/模型训练/classified/全/contour")
write.csv(contour,"contour.csv",row.names = FALSE)
#简单验证
setwd("F:/study/SRTP/模型训练/data plot/全")
plot(c(contour.x[1,]),c(contour.y[1,]),pch=20,xlim = c(0,1),ylim = c(0,1),xlab = "x",ylab = "y",col = "red")
points(c(contour.x[6,]),c(contour.y[6,]),pch=20,col = "blue")
points(c(contour.x[9,]),c(contour.y[9,]),pch=20,col = "green")
points(c(contour.x[7,]),c(contour.y[7,]),pch=20,col = "yellow")

#contour1对contour二次标准化，横纵坐标选用不同的单位长度，使脸部最长与最宽相等，用于区分胖脸和瘦脸####
contour1 = contour
for(i in 1:19*2){
  contour1[,i] = contour[,i]/contour[,2]
}
contour1.x = contour1[,1:19*2-1]
contour1.y = contour1[,1:19*2]
#图片输出
setwd("F:/study/SRTP/模型训练/classified/全/contour1/散点图")
for(i in 1:62){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(contour1.x[i,]),c(contour1.y[i,]),pch=20,xlim = c(0,1),ylim = c(0,1),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/data plot/全")
#聚类分析
contour1.d = dist(contour1)
contour1.hc = hclust(contour1.d)
plot(contour1.hc,hang = -1)
rect.hclust(contour1.hc, k=2, border="red")
#输出类信息
contour1.group = cutree(contour1.hc,k=2)
for(i in 1:62){
  if(contour1.group[i]==1) contour1$class[i]= 1;
  if(contour1.group[i]==2) contour1$class[i]= 2;
}
#输出csv
setwd("F:/study/SRTP/模型训练/classified/全/contour1")
write.csv(contour1,"contour1.csv",row.names = FALSE)
#简单验证
setwd("F:/study/SRTP/模型训练/data plot/全")
plot(c(contour1.x[1,]),c(contour1.y[1,]),pch=20,xlim = c(0,1),ylim = c(0,1),xlab = "x",ylab = "y",col = "red")
points(c(contour1.x[16,]),c(contour1.y[16,]),pch=20,col = "blue")
points(c(contour1.x[43,]),c(contour1.y[43,]),pch=20)
points(c(contour1.x[2,]),c(contour1.y[2,]),pch=20,col = "green")
points(c(contour1.x[3,]),c(contour1.y[3,]),pch=20,col = "yellow")

#eyebrow####
#默认眉毛对称，以左眉作为分类训练集，59~74共8个点，16个特征值
eyebrow = data[,59:74]
#数据标准化，left_eyebrow_lower_middle为原点，脸部最宽为单位长度（已设定好）
eyebrow_norm = eyebrow
for(i in 1:66){
  for(j in 1:8){
    eyebrow_norm[i,j*2-1] = eyebrow[i,j*2-1] - eyebrow[i,5]
    eyebrow_norm[i,j*2] = eyebrow[i,j*2] - eyebrow[i,6]
  }
}
eyebrow.x = eyebrow_norm[,1:8*2-1]
eyebrow.y = eyebrow_norm[,1:8*2]
#图片输出
setwd("F:/study/SRTP/模型训练/classified/全/eyebrow/散点图")
for(i in 1:66){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(eyebrow.x[i,]),c(eyebrow.y[i,]),pch=20,xlim = c(-0.2,0.2),ylim = c(-0.1,0.1),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/data plot/全")
#聚类分析
eyebrow.d = dist(eyebrow_norm)
eyebrow.hc = hclust(eyebrow.d)
plot(eyebrow.hc,hang = -1)
rect.hclust(eyebrow.hc, k=6, border="red")
#输出类信息，class=4,5废弃，实际上为4类
eyebrow.group = cutree(eyebrow.hc,k=6)
for(i in 1:66){
  if(eyebrow.group[i]==1) eyebrow_norm$class[i]= 1;
  if(eyebrow.group[i]==2) eyebrow_norm$class[i]= 2;
  if(eyebrow.group[i]==3) eyebrow_norm$class[i]= 3;
  if(eyebrow.group[i]==4) eyebrow_norm$class[i]= 4;
  if(eyebrow.group[i]==5) eyebrow_norm$class[i]= 5;
  if(eyebrow.group[i]==6) eyebrow_norm$class[i]= 6;
}
#输出csv,其中8(class=4),11(class=5)为废弃点
setwd("F:/study/SRTP/模型训练/classified/全/eyebrow")
write.csv(eyebrow_norm,"eyebrow.csv",row.names = FALSE)

#简单验证
setwd("F:/study/SRTP/模型训练/data plot/全")
#1
plot(c(eyebrow.x[1,]),c(eyebrow.y[1,]),pch=20,col="green",xlim = c(-0.2,0.2),ylim = c(-0.1,0.1))
points(c(eyebrow.x[4,]),c(eyebrow.y[4,]),pch=20,col="red")
#2
points(c(eyebrow.x[2,]),c(eyebrow.y[2,]),pch=20,col="blue")
points(c(eyebrow.x[30,]),c(eyebrow.y[30,]),pch=20,col="yellow")
#3
points(c(eyebrow.x[3,]),c(eyebrow.y[3,]),pch=20,col="blue")
points(c(eyebrow.x[28,]),c(eyebrow.y[28,]),pch=20,col="yellow")
#6
points(c(eyebrow.x[13,]),c(eyebrow.y[13,]),pch=20,col="blue")
points(c(eyebrow.x[15,]),c(eyebrow.y[15,]),pch=20,col="yellow")


#eye####
#默认眼睛对称，以左眼作为分类训练集，39~58共10个点，20个特征值,去除眼白left_eye_pupil的关键点49、50，共18个特征值
eye = data[,c(39:48,51:58)]
#数据标准化，以left_eye_center作为原点，脸部最宽为单位长度
eye_norm = eye
for(i in 1:66){
  for(j in 1:9){
    eye_norm[i,j*2-1] = eye[i,j*2-1] - eye[i,3]
    eye_norm[i,j*2] = eye[i,j*2] - eye[i,4]
  }
}
eye.x = eye_norm[,1:9*2-1]
eye.y = eye_norm[,1:9*2]
#图片输出
setwd("F:/study/SRTP/模型训练/classified/全/eye/散点图")
for(i in 1:66){
  jpeg(filename = paste(i,".jpg",sep = ""))
  plot(c(eye.x[i,]),c(eye.y[i,]),pch=20,xlim = c(-0.2,0.2),ylim = c(-0.1,0.1),xlab = "x",ylab = "y",main = i)
  dev.off()
}
setwd("F:/study/SRTP/模型训练/data plot/全")
#聚类分析
eye.d = dist(eye_norm)
eye.hc = hclust(eye.d)
plot(eye.hc,hang = -1)
rect.hclust(eye.hc, k=9, border="red")
#输出类信息，class=9、6、3废弃，实际上为6类
eye.group = cutree(eye.hc,k=9)
for(i in 1:66){
  if(eye.group[i]==1) eye_norm$class[i]= 1;
  if(eye.group[i]==2) eye_norm$class[i]= 2;
  if(eye.group[i]==3) eye_norm$class[i]= 3;
  if(eye.group[i]==4) eye_norm$class[i]= 4;
  if(eye.group[i]==5) eye_norm$class[i]= 5;
  if(eye.group[i]==6) eye_norm$class[i]= 6;
  if(eye.group[i]==7) eye_norm$class[i]= 7;
  if(eye.group[i]==8) eye_norm$class[i]= 8;
  if(eye.group[i]==9) eye_norm$class[i]= 9;
}
#输出csv,其中废弃数据点64(class=9),25(class=6),4、11、13(class=3)，
setwd("F:/study/SRTP/模型训练/classified/全/eye")
write.csv(eye_norm,"eye.csv",row.names = FALSE)

#简单验证
setwd("F:/study/SRTP/模型训练/data plot/全")
#1
plot(c(eye.x[44,]),c(eye.y[44,]),pch=20,col="green",xlim = c(-0.2,0.2),ylim = c(-0.1,0.1))
points(c(eye.x[46,]),c(eye.y[46,]),pch=20,col="blue")
points(c(eye.x[4,]),c(eye.y[4,]),pch=20,col="red")
