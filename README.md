# Split OpenFeature Java SDK Demo

This example here uses the Split Java OpenFeature SDK and the [pun api](https://www.punapi.rest) to serve a little page with jokes or puns. 

![image](https://github.com/kleinjoshuaa/java-spring-openfeature/assets/1207274/2ea5c177-c3cb-4fa2-8244-2e7295eeaf9a)

Click the button at the bottom to get a new Meme! (Or only a pun if the user ID doesn't fall into the experiment)


## Feature Flag Setup
Ensure that you have a feature flag entitled `openfeature_demo_flag` with treatments named `on` and `off`
![image](https://github.com/kleinjoshuaa/java-spring-openfeature/assets/1207274/1197eda3-4a88-43e2-b601-68244f95d237)

The sample code creates a random ID for each request - and the Feature Flag configuration will show you a meme with an image if the feature is on, or just a pun if it's disabled. 

![image](https://github.com/kleinjoshuaa/java-spring-openfeature/assets/1207274/e84cafd8-7b52-415a-a61c-c612ec749f74)


## How to run
Compile with maven to get the JAR file for the project. Once you have the jar file you will need to run with the additional command line argument `-DsdkKey='SDK KEY'` in order for the OpenFeature SDK to use the Split provider successfully. 

This uses spring boot - once the server is running (by default on port 8080 - navigate to localhost:8080 to see the app in action!


## Hooks
An afterHook is used to post the evaluation information to the command line, showing how hooks work with the Split provider
![image](https://github.com/kleinjoshuaa/java-spring-openfeature/assets/1207274/8e49f621-0609-4cc2-8bfd-df2100ff8942)
![image](https://github.com/kleinjoshuaa/java-spring-openfeature/assets/1207274/b2df50a3-a370-41c5-8986-551330c545fe)
