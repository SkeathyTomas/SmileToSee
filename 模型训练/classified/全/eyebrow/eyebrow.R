library("e1071")

data = read.csv("F:/study/SRTP/模型训练/classified/全/eyebrow/eyebrow_select.csv")#训练集
testdata = read.csv("F:/study/SRTP/模型训练/classified/全/eyebrow/eyebrow.csv")#测试集

x = data[,1:16]#特征变量
y = data[,17]#结果变量

modelx = x
modely = y
model = svm(modelx,modely,type = "C-classification",kernel = "radial",gamma = if(is.vector(modelx)) 1 else 1/ncol(modelx))

#列表展示
pred = predict(model,x)
table(pred,y)

#误差率
testx = testdata[,1:16]
testy = testdata[,17]
mean(y != predict(model,x))#训练集误差0.05555556
mean(testy != predict(model,testx))#测试集误差0.1875
