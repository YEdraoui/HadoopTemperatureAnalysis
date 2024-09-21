# Hadoop Temperature Analysis Project

This project demonstrates how to analyze large climate datasets using Apache Hadoop and the MapReduce framework. The goal of this project is to find the maximum temperature recorded for each year from the **Global Land Temperatures by City** dataset.

## Project Overview

- **Input Dataset**: GlobalLandTemperaturesByCity.csv
- **Technologies Used**: 
  - Apache Hadoop (HDFS, MapReduce)
  - Docker (for setting up Hadoop environment)
  - Java (for MapReduce implementation)
- **Goal**: Find the maximum temperature recorded for each year using a MapReduce job.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Dataset](#dataset)
- [Project Setup](#project-setup)
- [How to Run the Project](#how-to-run-the-project)
- [Output](#output)
- [Files in the Repository](#files-in-the-repository)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Make sure you have the following installed before running the project:

- [Docker](https://docs.docker.com/get-docker/)
- Basic understanding of how Hadoop works
- Java JDK installed on the host machine for compiling the MapReduce job

## Dataset

The dataset used for this project is the [GlobalLandTemperaturesByCity.csv](https://www.kaggle.com/berkeleyearth/climate-change-earth-surface-temperature-data) from Kaggle, which contains daily average temperatures for cities around the world.

1. **Download the dataset** from Kaggle [here](https://www.kaggle.com/berkeleyearth/climate-change-earth-surface-temperature-data).
2. After downloading, place the file in the `/input/` directory (or wherever appropriate) in your working directory.

## Project Setup

### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/hadoop-temperature-analysis.git
cd hadoop-temperature-analysis
