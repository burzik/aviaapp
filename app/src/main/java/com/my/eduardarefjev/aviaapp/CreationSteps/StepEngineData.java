package com.my.eduardarefjev.aviaapp.CreationSteps;

import java.util.Date;

/**
 * Created by  EduardArefjev on 25/10/2017.
 */

public class StepEngineData {
    public String engineName;
    public int engineId;
    public int planeId;
    public int planeBoardId;
    public Date launchDate;
    public String launchId; //WP no?
    public int atmPressure; //Pnv
    public int atmTemp; //Tnv
    public int limb; //По лимбу
    public int APUTime; //Tзап ВСУ

    //launch
    public float launchEngineTemp; //T c двигателя заброс ???
    public int launchAPUOffEngine; // n1 отключения ВСУ ???
    public int launchEngineTime; // время запуска двигателя

    //small gas
    public float modeSmallGasHPCSpeed; //n1
    public int modeSmallGasTemp; //Trc
    public float modeSmallGasOilPressure; //Pm
    public int modeSmallGasOilTemp; //TmC
    public int modeSmallGasFuelPressure; //Pt
    public int modeSmallGasVibration; //sqrt двигателя
    public int modeSmallGasHSPressure; // основная р система
    public int modeSmallGasHSPressureEmergency; //Аварийная при перекл.
    public int modeSmallGasBrakePressure; //Pторм основное
    public int modeSmallGasBrakePressureStop; //Pторм стояночное
    public int modeSmallGasGenerator; //Vгенератора
    public float modeSmallGasPressureWing; //Крал баки
    public float modeSmallGasPressureHA; //г аккумулятора
    public float modeSmallGasPressureHT; //г бак
    public boolean modeSmallGasLightsClosure; //закрытие замков фонаря
    public boolean modeSmallGasConditioning; //включение кондиционирования
    public float modeSmallGasCabin; //Pкабинsы

    //stage5
    public float stage5ModeName; //прямой ход
    public int stage5N1FlightTakeoff; //полет - взлет
    public int stage5N1TakeoffLanding; //взлет - посадка
    public int stage5N1FlightLanding; //полет - посадка
    public int stage5N1Prc; //prc
    public int stage5N1Release; //выпуск
    public int stage5N1Cleaning; //Уборка
    public int stage5N1BrakePrc;//Тормоз prc
    public int stage5N1Tmc; //Tmc
    public int stage5N1VGenerator; //Vгенератора

    //stage3
    public int stage3ModeName; //прямой ход

    //0.85 Nom
    public float mode85NomHPCSpeed; //n1
    public int mode85NomTemp;
    public float mode85NomOilPressure;
    public int mode85NomOilTemp;
    public int mode85NomFuelPressure;
    public int mode85NomVibration;

    //n=100
    public int mode100Vibration;

    //Nom
    public float modeNomHPCSpeed; //n1
    public int modeNomTemp;
    public float modeNomOilPressure;
    public int modeNomOilTemp;
    public int modeNomFuelPressure;
    public int modeNomVibration;
    public boolean modeNom4001OnSignal;
    public boolean modeNom4001OnEngine;
    public boolean modeNom4001OffSignal;
    public boolean modeNom4001OffEngine;
    public boolean modeNomPTBKHeat;
    public boolean modeNomPTBKCold;
    public boolean modeNomPTBKAutomation;
    public boolean modeNomShowerHeat;
    public boolean modeNomShowerCold;
    public boolean modeNomShowerAutomation;
    public int modeNomVGenerator;

    //Max
    public float modeMaxHPCSpeed;
    public float modeMaxHPCSpeedN2;
    public int modeMaxTemp;
    public float modeMaxOilPressure;
    public int modeMaxOilTemp;
    public int modeMaxFuelPressure;
    public int modeMaxVibration;
    public int modeMaxVGenerator;
    public boolean modeMaxGeneratorSpeedConst;
    public float modeMaxPressureCabin;
    public float modeMaxPressureWings;
    public int modeMaxPressureNozzles;

    //stageN
    public int stageN3ModeName;
    public int stageN5ModeName;

    //acceleration
    public float modeAccelerationIdleMaxIn;
    public float modeAccelerationIdleOut;

    //mode Small Gas 2
    public float modeSmallGas2HPCSpeed; //n1
    public int modeSmallGas2Temp; //Trc
    public float modeSmallGas2OilPressure; //Pm
    public int modeSmallGas2OilTemp; //TmC
    public int modeSmallGas2FuelPressure; //Pt
    public int modeSmallGas2Vibration; //sqrt двигателя
    public float modeSmallGas2SwitchMin;
    public float modeSmallGas2SwitchMax;
    public boolean modeSmallGas2CondOn;
    public boolean modeSmallGas2CondOff;
    public boolean modeSmallGas2AntifreezeOn;
    public boolean modeSmallGas2AntifreezeOff;

    //mode KND
    public float modeKVDHPCSpeed;
    public float modeKNDHPCSpeed;
    public float modeKNDHPCSpeedCited;
    public float modeKNDHPCSpeedInstalled;

    //mode Work
    public float modeWorkSum;
    public float modeWokrNom;
    public float modeWorkMax;
    public int modeWorkLaunchCount;

}
