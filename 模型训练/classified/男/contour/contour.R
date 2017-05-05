library("e1071")

data = read.csv("F:/study/SRTP/模型训练/classified/男/contour/contour.csv")

x = data[,1:38] #特征变量
y = data[,39] #结果变量
modelx = x[1:8,]
modely = y[1:8]
model = svm(modelx,modely,kernel = "radial",gamma = if(is.vector(modelx)) 1 else 1/ncol(modelx))

#列表展示
pred = predict(model,x)
table(pred,y)

#误差率
mean(y != predict(model,x))
#1~8最小误差3.57%
