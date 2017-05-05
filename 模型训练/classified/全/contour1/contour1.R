library("e1071")

data = read.csv("F:/study/SRTP/模型训练/classified/全/contour1/contour1_select.csv")#训练集
testdata = read.csv("F:/study/SRTP/模型训练/classified/全/contour1/contour1.csv")#测试集

x = data[,1:38]#特征变量
y = data[,39]#结果变量

modelx = x[16:19,]
modely = y[16:19]
model = svm(modelx,modely,type = "C-classification",kernel = "radial",gamma = if(is.vector(modelx)) 1 else 1/ncol(modelx))

#列表展示
pred = predict(model,x)
table(pred,y)

#误差率
testx = testdata[,1:38]
testy = testdata[,39]
mean(y != predict(model,x))#训练集误差
mean(testy != predict(model,testx))#测试集误差
