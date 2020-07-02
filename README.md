# SmileToSee

It's an App project to automatically transform a real human face to a cartoon style face. ——By Lingang Wu-ZJU

Android--an Android App framework for the project

Java--Java project with model training and basic function

## Main Ideas

We apply [face++](https://www.faceplusplus.com.cn/) to detect the face landmarks of a real face and use support vector machine to learn the feature of landmarks. So with the pre-drawn cartoon style face materials, the system can automatically select proper materials (e.g., face sizes, eyes, and mouths) and construct a cartoon style face.

## Examples of Results

![example](README.assets/image-20200702135222715.png)
