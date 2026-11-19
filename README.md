# ConcreteStrength-ANN-SHAP
Offline Mobile Prediction and SHAP-Based Interpretation of High-Performance Concrete Compressive Strength Using ANN
Overview
This repository provides the research materials and implementation resources associated with the study:
"Offline Mobile Prediction and Local SHAP Interpretation of High-Performance Concrete Compressive Strength Using ANN"
The project presents an explainable, offline Android-based decision-support application for predicting the compressive strength of high-performance concrete (HPC) using an artificial neural network (ANN). In addition to predicting compressive strength, the application provides local feature-level explanations using SHapley Additive exPlanations (SHAP).
The primary objective is to integrate machine-learning-based concrete strength prediction and explainability into a mobile application that can operate without an internet connection.
Key Features
•	Offline prediction: Predicts concrete compressive strength directly on an Android device without requiring an internet connection.
•	ANN-based prediction: Uses an optimized multilayer perceptron (MLP) regression model.
•	Local explainability: Calculates SHAP values to explain the contribution of individual input variables to each prediction.
•	Exhaustive SHAP calculation: Enumerates all possible feature coalitions for the eight input variables.
•	Adjustable background dataset: Supports different numbers of background observations for SHAP computation, depending on the application configuration.
•	Visual interpretation: Presents feature contributions through a SHAP waterfall-style visualization.

Input Variables
The model uses eight concrete mixture and curing parameters to estimate compressive strength.
Abbreviation	Input variable
C	Cement content
BFS	Blast furnace slag content
FA	Fly ash content
W	Water content
SP	Superplasticizer content
CA	Coarse aggregate content
FAg	Fine aggregate content
Age	Curing age
Target variable: Concrete compressive strength (CS).
The input variables should be provided using the same units and feature order used during model training.

