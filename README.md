# A 2D shape plotter leJOS NXT robot

[![GitHub repo size](https://img.shields.io/github/repo-size/brianpinto91/leJOS-NXT-2D-plotter?logo=GitHub)]()
[![CodeFactor Grade](https://img.shields.io/codefactor/grade/github/brianpinto91/leJOS-NXT-2D-plotter/main)](https://www.codefactor.io/repository/github/brianpinto91/leJOS-NXT-2D-plotter)

This project is a Java application for a leJOS mindstorm robot to draw shapes like a diamond inside a rectangle or an anchor. This program can be extended to plot other shapes.

## Table of Contents
* [Robot](#Robot)
* [Installation](#Installation)
* [Technologies](#Technologies)
* [License](#License)

## Robot

The robot is built using [Lego Mindstorms NXT Robotic kit][lego_robot_kit_link]. At the heart of the robot is the [NXT brick][nxt_brick_link] on which the JAVA application runs using LeJOS firmware version 0.9.1 beta.

The assembled robot along with the plotting board is shown in the below figure.

[![leJOS Robot](github-page/static/img/leJOS_robot.png)]()

The robot has three motors: one motor drives the robot forward and backward. Another one moves the swivel arm, on which the pen is mounted, left and right. The third motor moves the pen up and down (by coiling a rope on a reel). Three sensors are installed for calibration purposes: a touch sensor allows determining when the motor driving the swivel arm is in its rightmost position. Another touch sensor detects if the pen reaches its maximum upper position. A light sensor enables the robot to find a black bar, which marks the start of the drawing area.

The motors and sensors are connected to the NXT brick as follows:

| Port | Usage |
| ---- | ----- |
| SensorPort.S1 | Touch sensor of Swivel arm |
| SensorPort.S2 | Touch sensor of pen |
| SensorPort.S3 | Light Sensor |
| Motor.A | Swivel arm rotation |
| Motor.B | Pen up/down |
| Motor.C | Wheels drive |

### Plotting-surface Coordinate System

The software uses a Cartesian coordinate system to define locations in two dimensions x and y. As shown in below figure, the y-direction is defined as the direction in which the robot moves forward and backward. The position y = 0 is located at the upper edge of the black bar (shown in gray). The x-direction is orthogonal to y and has its x = 0 value in the middle of the robot which is the horizontal midway on the plotting surface in ideal case when the robot is exactly placed in the middle before starting. Otjerwise the middle of the robot along the horizontal direction at which the robot is placed is considered to be x=0. The radius of the swivel arm holding the pen is represented by 'r' in the figure. The robot cannot move in the x-direction. So the limits of the points that can be reached along the x-axis is determined by the length of the swivel arm and its maximum rotation angle determined through calibration before plotting.

<div style="text-align:center;"><img src=github-page/static/img/coordinate_system.png height=400>
</div>

Since the robot cannot move in the horizontal direction, plotting at points other than along the y-axis is challenging. Co-ordinated wheel and swivel arm movement is used for this purpose using mathematical calculations and is implemented in the software.

## Installation

The instructions here are for installation using a Linux host machine. For Windows host machine refer the documentation [here][windows_guide_link].

On your computer, first navigate to the directory were you want to download the repository to. Then run the following commands:

```sh
git clone --depth 1 https://github.com/brianpinto91/leJOS-NXT-2D-plotter.git
```
 
There are two options to install the software on the NXT brick

### Using the leJOS NXJ command line tools on Linux

The Java code uses the leJOS_NXJ library to interface with the sensors, the motors, and also the buttons on the NXT brick. This requires the compilation and linking to be done using the **leJOS SDK**. Follow this [guide][lejos_sdk_link] to download and install it.

Create a new directory **build** inside the parent **leJOS-NXT-2D-plotter** directory. Then use the below command to compile the source java files into this new directory. (Make sure to navigate to the parent directory on the command line before executing this command)

```sh
nxjc -d build src/plotbot/*.java
```

If this step is successful, you will find the compiled **.class** files for all the **.java** files in the **build** directory. 

The next step is to link the leJOS class library and create an executable. The main class file is the **Plotbot.class**. Use the below commands sequentially to create an executable:

```sh
cd build
nxjlink -v -o plotter.nxj plotbot/Plotbot
```
You will find **plotter.nxj** executable file created in the **build** directory. This file is now ready to be uploaded to the NXT brick.

Make sure that your NXT brick is connected to the host PC using either USB or bluetooth. Then run the following command:

```sh
nxjupload plotter.nxj
```
Now you are ready to use the NXT brick on the robot to plot different shapes!

### Installation using Eclipse IDE

For this you need to install [Eclipse IDE][eclipse_link] and the leJOS Eclipse plugin as described in this [guide][lejos_plugin_link].

Open the cloned github repository in **Eclipse**. In the project explorer on the left side right click on the parent directory. In the list you will find an option **leJOS NXJ**. Expand this option and click on **Convert to leJOS NXJ project**.

Then, in the top navigation bar choose **Window --> ShowView --> Ant**. A new window titled **Ant** appears (on right hand
side, near Outline in case you use the default layout). Drag and drop the file build.xml from the project explorer into the Ant window. You will now see various options in the Ant window.

Connect the NXT brick using bluettoth or usb and then select **Upload** option in the Ant window and click on the play button. That's it, your robot is ready to plot.

## Technologies
[![forthebadge-java](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)<br>

<img target="_blank" src="github-page/static/img/lejosLogo.jpeg" height=70><br>

<img target="_blank" src="github-page/static/img/eclipse_logo_colour.png" height=50><br>

## License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE.md)

Copyright 2020 Brian Pinto

[nxt_brick_link]: http://www.lejos.org/nxj.php
[lego_robot_kit_link]: https://www.generationrobots.com/en/401351-robotic-kit-lego-mindstorms-nxt-v2-robot.html#
[lejos_sdk_link]: http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/GettingStartedLinux.htm
[windows_guide_link]: http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/GettingStartedWindows.htm
[lejos_plugin_link]: http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/UsingEclipse.htm
[eclipse_link]: https://www.eclipse.org/downloads/packages/release/helios/m7/eclipse-ide-linux-developers