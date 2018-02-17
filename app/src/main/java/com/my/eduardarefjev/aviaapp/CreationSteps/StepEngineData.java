package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	25.10.2017		Eduard Arefjev 		Created main information
 * 	30.12.2017      Eduard Arefjev      implemented parcelable
 *  30.12.2017      Eduard Arefjev      Added new fields
 *  17.02.2018      Eduard Arefjev      Added new field
 */

public class StepEngineData implements Parcelable{

    StepEngineData(){

    }

    private String rowId;

    private String engineName;
    private int engineId;
    private int planeId;
    private int planeBoardId;
    private Date launchDate;

    private float work0Nominal;
    private float workNominal;
    private float workMax;

    private String launchId; //WP no?
    private int atmPressure; //Pnv
    private int atmTemp; //Tnv
    public int limb; //По лимбу
    private int apuTime; //Tзап ВСУ

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
    public float modeSmallGas2CondOn;
    public float modeSmallGas2CondOff;
    public float modeSmallGas2AntifreezeOn;
    public float modeSmallGas2AntifreezeOff;

    //mode KND

    public float modeKNDNKVDPHY97;
    public float modeKNDNKVDPHY99;
    public float modeKNDNKVDPHY101;
    public float modeKNDNKNDPHY97;
    public float modeKNDNKNDPHY99;
    public float modeKNDNKNDPHY101;
    public float modeKNDNKNDPLANE97;
    public float modeKNDNKNDPLANE99;
    public float modeKNDNKNDPLANE101;

    //rotors
    public int modeRotorVD;
    public int modeRotorND;

    //tablo
    public int modeGeneratorTablo;

    //mode Work
    public float modeWorkSum;
    public float modeWorkNom;
    public float modeWorkMax;
    public int modeWorkLaunchCount;
    public int modeWorkLaunchVSUCount;
    public int modeWorkN1Count;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String currentUserId;

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public int getPlaneBoardId() {
        return planeBoardId;
    }

    public void setPlaneBoardId(int planeBoardId) {
        this.planeBoardId = planeBoardId;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public String getLaunchId() {
        return launchId;
    }

    public void setLaunchId(String launchId) {
        this.launchId = launchId;
    }

    public float getWork0Nominal() {
        return work0Nominal;
    }

    public void setWork0Nominal(float Work0Nominal) {
        this.work0Nominal = Work0Nominal;
    }

    public float getWorkNominal() {
        return workNominal;
    }

    public void setWorkNominal(float WorkNominal) {
        this.workNominal = WorkNominal;
    }

    public float getWorkMax() {
        return workMax;
    }

    public void setWorkMax(float WorkMax) {
        this.workMax = WorkMax;
    }

    public int getAtmPressure() {
        return atmPressure;
    }

    public void setAtmPressure(int atmPressure) {
        this.atmPressure = atmPressure;
    }

    public int getAtmTemp() {
        return atmTemp;
    }

    public void setAtmTemp(int atmTemp) {
        this.atmTemp = atmTemp;
    }

    public int getLimb() {
        return limb;
    }

    public void setLimb(int limb) {
        this.limb = limb;
    }

    public int getApuTime() {
        return apuTime;
    }

    public void setApuTime(int apuTime) {
        this.apuTime = apuTime;
    }

    public float getLaunchEngineTemp() {
        return launchEngineTemp;
    }

    public void setLaunchEngineTemp(float launchEngineTemp) {
        this.launchEngineTemp = launchEngineTemp;
    }

    public int getLaunchAPUOffEngine() {
        return launchAPUOffEngine;
    }

    public void setLaunchAPUOffEngine(int launchAPUOffEngine) {
        this.launchAPUOffEngine = launchAPUOffEngine;
    }

    public int getLaunchEngineTime() {
        return launchEngineTime;
    }

    public void setLaunchEngineTime(int launchEngineTime) {
        this.launchEngineTime = launchEngineTime;
    }

    public float getModeSmallGasHPCSpeed() {
        return modeSmallGasHPCSpeed;
    }

    public void setModeSmallGasHPCSpeed(float modeSmallGasHPCSpeed) {
        this.modeSmallGasHPCSpeed = modeSmallGasHPCSpeed;
    }

    public int getModeSmallGasTemp() {
        return modeSmallGasTemp;
    }

    public void setModeSmallGasTemp(int modeSmallGasTemp) {
        this.modeSmallGasTemp = modeSmallGasTemp;
    }

    public float getModeSmallGasOilPressure() {
        return modeSmallGasOilPressure;
    }

    public void setModeSmallGasOilPressure(float modeSmallGasOilPressure) {
        this.modeSmallGasOilPressure = modeSmallGasOilPressure;
    }

    public int getModeSmallGasOilTemp() {
        return modeSmallGasOilTemp;
    }

    public void setModeSmallGasOilTemp(int modeSmallGasOilTemp) {
        this.modeSmallGasOilTemp = modeSmallGasOilTemp;
    }

    public int getModeSmallGasFuelPressure() {
        return modeSmallGasFuelPressure;
    }

    public void setModeSmallGasFuelPressure(int modeSmallGasFuelPressure) {
        this.modeSmallGasFuelPressure = modeSmallGasFuelPressure;
    }

    public int getModeSmallGasVibration() {
        return modeSmallGasVibration;
    }

    public void setModeSmallGasVibration(int modeSmallGasVibration) {
        this.modeSmallGasVibration = modeSmallGasVibration;
    }

    public int getModeSmallGasHSPressure() {
        return modeSmallGasHSPressure;
    }

    public void setModeSmallGasHSPressure(int modeSmallGasHSPressure) {
        this.modeSmallGasHSPressure = modeSmallGasHSPressure;
    }

    public int getModeSmallGasHSPressureEmergency() {
        return modeSmallGasHSPressureEmergency;
    }

    public void setModeSmallGasHSPressureEmergency(int modeSmallGasHSPressureEmergency) {
        this.modeSmallGasHSPressureEmergency = modeSmallGasHSPressureEmergency;
    }

    public int getModeSmallGasBrakePressure() {
        return modeSmallGasBrakePressure;
    }

    public void setModeSmallGasBrakePressure(int modeSmallGasBrakePressure) {
        this.modeSmallGasBrakePressure = modeSmallGasBrakePressure;
    }

    public int getModeSmallGasBrakePressureStop() {
        return modeSmallGasBrakePressureStop;
    }

    public void setModeSmallGasBrakePressureStop(int modeSmallGasBrakePressureStop) {
        this.modeSmallGasBrakePressureStop = modeSmallGasBrakePressureStop;
    }

    public int getModeSmallGasGenerator() {
        return modeSmallGasGenerator;
    }

    public void setModeSmallGasGenerator(int modeSmallGasGenerator) {
        this.modeSmallGasGenerator = modeSmallGasGenerator;
    }

    public float getModeSmallGasPressureWing() {
        return modeSmallGasPressureWing;
    }

    public void setModeSmallGasPressureWing(float modeSmallGasPressureWing) {
        this.modeSmallGasPressureWing = modeSmallGasPressureWing;
    }

    public float getModeSmallGasPressureHA() {
        return modeSmallGasPressureHA;
    }

    public void setModeSmallGasPressureHA(float modeSmallGasPressureHA) {
        this.modeSmallGasPressureHA = modeSmallGasPressureHA;
    }

    public float getModeSmallGasPressureHT() {
        return modeSmallGasPressureHT;
    }

    public void setModeSmallGasPressureHT(float modeSmallGasPressureHT) {
        this.modeSmallGasPressureHT = modeSmallGasPressureHT;
    }

    public boolean isModeSmallGasLightsClosure() {
        return modeSmallGasLightsClosure;
    }

    public void setModeSmallGasLightsClosure(boolean modeSmallGasLightsClosure) {
        this.modeSmallGasLightsClosure = modeSmallGasLightsClosure;
    }

    public boolean isModeSmallGasConditioning() {
        return modeSmallGasConditioning;
    }

    public void setModeSmallGasConditioning(boolean modeSmallGasConditioning) {
        this.modeSmallGasConditioning = modeSmallGasConditioning;
    }

    public float getModeSmallGasCabin() {
        return modeSmallGasCabin;
    }

    public void setModeSmallGasCabin(float modeSmallGasCabin) {
        this.modeSmallGasCabin = modeSmallGasCabin;
    }

    public float getStage5ModeName() {
        return stage5ModeName;
    }

    public void setStage5ModeName(float stage5ModeName) {
        this.stage5ModeName = stage5ModeName;
    }

    public int getStage5N1FlightTakeoff() {
        return stage5N1FlightTakeoff;
    }

    public void setStage5N1FlightTakeoff(int stage5N1FlightTakeoff) {
        this.stage5N1FlightTakeoff = stage5N1FlightTakeoff;
    }

    public int getStage5N1TakeoffLanding() {
        return stage5N1TakeoffLanding;
    }

    public void setStage5N1TakeoffLanding(int stage5N1TakeoffLanding) {
        this.stage5N1TakeoffLanding = stage5N1TakeoffLanding;
    }

    public int getStage5N1FlightLanding() {
        return stage5N1FlightLanding;
    }

    public void setStage5N1FlightLanding(int stage5N1FlightLanding) {
        this.stage5N1FlightLanding = stage5N1FlightLanding;
    }

    public int getStage5N1Prc() {
        return stage5N1Prc;
    }

    public void setStage5N1Prc(int stage5N1Prc) {
        this.stage5N1Prc = stage5N1Prc;
    }

    public int getStage5N1Release() {
        return stage5N1Release;
    }

    public void setStage5N1Release(int stage5N1Release) {
        this.stage5N1Release = stage5N1Release;
    }

    public int getStage5N1Cleaning() {
        return stage5N1Cleaning;
    }

    public void setStage5N1Cleaning(int stage5N1Cleaning) {
        this.stage5N1Cleaning = stage5N1Cleaning;
    }

    public int getStage5N1BrakePrc() {
        return stage5N1BrakePrc;
    }

    public void setStage5N1BrakePrc(int stage5N1BrakePrc) {
        this.stage5N1BrakePrc = stage5N1BrakePrc;
    }

    public int getStage5N1Tmc() {
        return stage5N1Tmc;
    }

    public void setStage5N1Tmc(int stage5N1Tmc) {
        this.stage5N1Tmc = stage5N1Tmc;
    }

    public int getStage5N1VGenerator() {
        return stage5N1VGenerator;
    }

    public void setStage5N1VGenerator(int stage5N1VGenerator) {
        this.stage5N1VGenerator = stage5N1VGenerator;
    }

    public int getStage3ModeName() {
        return stage3ModeName;
    }

    public void setStage3ModeName(int stage3ModeName) {
        this.stage3ModeName = stage3ModeName;
    }

    public float getMode85NomHPCSpeed() {
        return mode85NomHPCSpeed;
    }

    public void setMode85NomHPCSpeed(float mode85NomHPCSpeed) {
        this.mode85NomHPCSpeed = mode85NomHPCSpeed;
    }

    public int getMode85NomTemp() {
        return mode85NomTemp;
    }

    public void setMode85NomTemp(int mode85NomTemp) {
        this.mode85NomTemp = mode85NomTemp;
    }

    public float getMode85NomOilPressure() {
        return mode85NomOilPressure;
    }

    public void setMode85NomOilPressure(float mode85NomOilPressure) {
        this.mode85NomOilPressure = mode85NomOilPressure;
    }

    public int getMode85NomOilTemp() {
        return mode85NomOilTemp;
    }

    public void setMode85NomOilTemp(int mode85NomOilTemp) {
        this.mode85NomOilTemp = mode85NomOilTemp;
    }

    public int getMode85NomFuelPressure() {
        return mode85NomFuelPressure;
    }

    public void setMode85NomFuelPressure(int mode85NomFuelPressure) {
        this.mode85NomFuelPressure = mode85NomFuelPressure;
    }

    public int getMode85NomVibration() {
        return mode85NomVibration;
    }

    public void setMode85NomVibration(int mode85NomVibration) {
        this.mode85NomVibration = mode85NomVibration;
    }

    public int getMode100Vibration() {
        return mode100Vibration;
    }

    public void setMode100Vibration(int mode100Vibration) {
        this.mode100Vibration = mode100Vibration;
    }

    public float getModeNomHPCSpeed() {
        return modeNomHPCSpeed;
    }

    public void setModeNomHPCSpeed(float modeNomHPCSpeed) {
        this.modeNomHPCSpeed = modeNomHPCSpeed;
    }

    public int getModeNomTemp() {
        return modeNomTemp;
    }

    public void setModeNomTemp(int modeNomTemp) {
        this.modeNomTemp = modeNomTemp;
    }

    public float getModeNomOilPressure() {
        return modeNomOilPressure;
    }

    public void setModeNomOilPressure(float modeNomOilPressure) {
        this.modeNomOilPressure = modeNomOilPressure;
    }

    public int getModeNomOilTemp() {
        return modeNomOilTemp;
    }

    public void setModeNomOilTemp(int modeNomOilTemp) {
        this.modeNomOilTemp = modeNomOilTemp;
    }

    public int getModeNomFuelPressure() {
        return modeNomFuelPressure;
    }

    public void setModeNomFuelPressure(int modeNomFuelPressure) {
        this.modeNomFuelPressure = modeNomFuelPressure;
    }

    public int getModeNomVibration() {
        return modeNomVibration;
    }

    public void setModeNomVibration(int modeNomVibration) {
        this.modeNomVibration = modeNomVibration;
    }

    public boolean isModeNom4001OnSignal() {
        return modeNom4001OnSignal;
    }

    public void setModeNom4001OnSignal(boolean modeNom4001OnSignal) {
        this.modeNom4001OnSignal = modeNom4001OnSignal;
    }

    public boolean isModeNom4001OnEngine() {
        return modeNom4001OnEngine;
    }

    public void setModeNom4001OnEngine(boolean modeNom4001OnEngine) {
        this.modeNom4001OnEngine = modeNom4001OnEngine;
    }

    public boolean isModeNom4001OffSignal() {
        return modeNom4001OffSignal;
    }

    public void setModeNom4001OffSignal(boolean modeNom4001OffSignal) {
        this.modeNom4001OffSignal = modeNom4001OffSignal;
    }

    public boolean isModeNom4001OffEngine() {
        return modeNom4001OffEngine;
    }

    public void setModeNom4001OffEngine(boolean modeNom4001OffEngine) {
        this.modeNom4001OffEngine = modeNom4001OffEngine;
    }

    public boolean isModeNomPTBKHeat() {
        return modeNomPTBKHeat;
    }

    public void setModeNomPTBKHeat(boolean modeNomPTBKHeat) {
        this.modeNomPTBKHeat = modeNomPTBKHeat;
    }

    public boolean isModeNomPTBKCold() {
        return modeNomPTBKCold;
    }

    public void setModeNomPTBKCold(boolean modeNomPTBKCold) {
        this.modeNomPTBKCold = modeNomPTBKCold;
    }

    public boolean isModeNomPTBKAutomation() {
        return modeNomPTBKAutomation;
    }

    public void setModeNomPTBKAutomation(boolean modeNomPTBKAutomation) {
        this.modeNomPTBKAutomation = modeNomPTBKAutomation;
    }

    public boolean isModeNomShowerHeat() {
        return modeNomShowerHeat;
    }

    public void setModeNomShowerHeat(boolean modeNomShowerHeat) {
        this.modeNomShowerHeat = modeNomShowerHeat;
    }

    public boolean isModeNomShowerCold() {
        return modeNomShowerCold;
    }

    public void setModeNomShowerCold(boolean modeNomShowerCold) {
        this.modeNomShowerCold = modeNomShowerCold;
    }

    public boolean isModeNomShowerAutomation() {
        return modeNomShowerAutomation;
    }

    public void setModeNomShowerAutomation(boolean modeNomShowerAutomation) {
        this.modeNomShowerAutomation = modeNomShowerAutomation;
    }

    public int getModeNomVGenerator() {
        return modeNomVGenerator;
    }

    public void setModeNomVGenerator(int modeNomVGenerator) {
        this.modeNomVGenerator = modeNomVGenerator;
    }

    public float getModeMaxHPCSpeed() {
        return modeMaxHPCSpeed;
    }

    public void setModeMaxHPCSpeed(float modeMaxHPCSpeed) {
        this.modeMaxHPCSpeed = modeMaxHPCSpeed;
    }

    public float getModeMaxHPCSpeedN2() {
        return modeMaxHPCSpeedN2;
    }

    public void setModeMaxHPCSpeedN2(float modeMaxHPCSpeedN2) {
        this.modeMaxHPCSpeedN2 = modeMaxHPCSpeedN2;
    }

    public int getModeMaxTemp() {
        return modeMaxTemp;
    }

    public void setModeMaxTemp(int modeMaxTemp) {
        this.modeMaxTemp = modeMaxTemp;
    }

    public float getModeMaxOilPressure() {
        return modeMaxOilPressure;
    }

    public void setModeMaxOilPressure(float modeMaxOilPressure) {
        this.modeMaxOilPressure = modeMaxOilPressure;
    }

    public int getModeMaxOilTemp() {
        return modeMaxOilTemp;
    }

    public void setModeMaxOilTemp(int modeMaxOilTemp) {
        this.modeMaxOilTemp = modeMaxOilTemp;
    }

    public int getModeMaxFuelPressure() {
        return modeMaxFuelPressure;
    }

    public void setModeMaxFuelPressure(int modeMaxFuelPressure) {
        this.modeMaxFuelPressure = modeMaxFuelPressure;
    }

    public int getModeMaxVibration() {
        return modeMaxVibration;
    }

    public void setModeMaxVibration(int modeMaxVibration) {
        this.modeMaxVibration = modeMaxVibration;
    }

    public int getModeMaxVGenerator() {
        return modeMaxVGenerator;
    }

    public void setModeMaxVGenerator(int modeMaxVGenerator) {
        this.modeMaxVGenerator = modeMaxVGenerator;
    }

    public boolean isModeMaxGeneratorSpeedConst() {
        return modeMaxGeneratorSpeedConst;
    }

    public void setModeMaxGeneratorSpeedConst(boolean modeMaxGeneratorSpeedConst) {
        this.modeMaxGeneratorSpeedConst = modeMaxGeneratorSpeedConst;
    }

    public float getModeMaxPressureCabin() {
        return modeMaxPressureCabin;
    }

    public void setModeMaxPressureCabin(float modeMaxPressureCabin) {
        this.modeMaxPressureCabin = modeMaxPressureCabin;
    }

    public float getModeMaxPressureWings() {
        return modeMaxPressureWings;
    }

    public void setModeMaxPressureWings(float modeMaxPressureWings) {
        this.modeMaxPressureWings = modeMaxPressureWings;
    }

    public int getModeMaxPressureNozzles() {
        return modeMaxPressureNozzles;
    }

    public void setModeMaxPressureNozzles(int modeMaxPressureNozzles) {
        this.modeMaxPressureNozzles = modeMaxPressureNozzles;
    }

    public int getStageN3ModeName() {
        return stageN3ModeName;
    }

    public void setStageN3ModeName(int stageN3ModeName) {
        this.stageN3ModeName = stageN3ModeName;
    }

    public int getStageN5ModeName() {
        return stageN5ModeName;
    }

    public void setStageN5ModeName(int stageN5ModeName) {
        this.stageN5ModeName = stageN5ModeName;
    }

    public float getModeAccelerationIdleMaxIn() {
        return modeAccelerationIdleMaxIn;
    }

    public void setModeAccelerationIdleMaxIn(float modeAccelerationIdleMaxIn) {
        this.modeAccelerationIdleMaxIn = modeAccelerationIdleMaxIn;
    }

    public float getModeAccelerationIdleOut() {
        return modeAccelerationIdleOut;
    }

    public void setModeAccelerationIdleOut(float modeAccelerationIdleOut) {
        this.modeAccelerationIdleOut = modeAccelerationIdleOut;
    }

    public float getModeSmallGas2HPCSpeed() {
        return modeSmallGas2HPCSpeed;
    }

    public void setModeSmallGas2HPCSpeed(float modeSmallGas2HPCSpeed) {
        this.modeSmallGas2HPCSpeed = modeSmallGas2HPCSpeed;
    }

    public int getModeSmallGas2Temp() {
        return modeSmallGas2Temp;
    }

    public void setModeSmallGas2Temp(int modeSmallGas2Temp) {
        this.modeSmallGas2Temp = modeSmallGas2Temp;
    }

    public float getModeSmallGas2OilPressure() {
        return modeSmallGas2OilPressure;
    }

    public void setModeSmallGas2OilPressure(float modeSmallGas2OilPressure) {
        this.modeSmallGas2OilPressure = modeSmallGas2OilPressure;
    }

    public int getModeSmallGas2OilTemp() {
        return modeSmallGas2OilTemp;
    }

    public void setModeSmallGas2OilTemp(int modeSmallGas2OilTemp) {
        this.modeSmallGas2OilTemp = modeSmallGas2OilTemp;
    }

    public int getModeSmallGas2FuelPressure() {
        return modeSmallGas2FuelPressure;
    }

    public void setModeSmallGas2FuelPressure(int modeSmallGas2FuelPressure) {
        this.modeSmallGas2FuelPressure = modeSmallGas2FuelPressure;
    }

    public int getModeSmallGas2Vibration() {
        return modeSmallGas2Vibration;
    }

    public void setModeSmallGas2Vibration(int modeSmallGas2Vibration) {
        this.modeSmallGas2Vibration = modeSmallGas2Vibration;
    }

    public float getModeSmallGas2SwitchMin() {
        return modeSmallGas2SwitchMin;
    }

    public void setModeSmallGas2SwitchMin(float modeSmallGas2SwitchMin) {
        this.modeSmallGas2SwitchMin = modeSmallGas2SwitchMin;
    }

    public float getModeSmallGas2SwitchMax() {
        return modeSmallGas2SwitchMax;
    }

    public void setModeSmallGas2SwitchMax(float modeSmallGas2SwitchMax) {
        this.modeSmallGas2SwitchMax = modeSmallGas2SwitchMax;
    }

    public float getModeSmallGas2CondOn() {
        return modeSmallGas2CondOn;
    }

    public void setModeSmallGas2CondOn(float modeSmallGas2CondOn) {
        this.modeSmallGas2CondOn = modeSmallGas2CondOn;
    }

    public float getModeSmallGas2CondOff() {
        return modeSmallGas2CondOff;
    }

    public void setModeSmallGas2CondOff(float modeSmallGas2CondOff) {
        this.modeSmallGas2CondOff = modeSmallGas2CondOff;
    }

    public float getModeSmallGas2AntifreezeOn() {
        return modeSmallGas2AntifreezeOn;
    }

    public void setModeSmallGas2AntifreezeOn(float modeSmallGas2AntifreezeOn) {
        this.modeSmallGas2AntifreezeOn = modeSmallGas2AntifreezeOn;
    }

    public float getModeSmallGas2AntifreezeOff() {
        return modeSmallGas2AntifreezeOff;
    }

    public void setModeSmallGas2AntifreezeOff(float modeSmallGas2AntifreezeOff) {
        this.modeSmallGas2AntifreezeOff = modeSmallGas2AntifreezeOff;
    }

    public float getModeKNDNKVDPHY97() {
        return modeKNDNKVDPHY97;
    }

    public void setModeKNDNKVDPHY97(float modeKNDNKVDPHY97) {
        this.modeKNDNKVDPHY97 = modeKNDNKVDPHY97;
    }

    public float getModeKNDNKVDPHY99() {
        return modeKNDNKVDPHY99;
    }

    public void setModeKNDNKVDPHY99(float modeKNDNKVDPHY99) {
        this.modeKNDNKVDPHY99 = modeKNDNKVDPHY99;
    }

    public float getModeKNDNKVDPHY101() {
        return modeKNDNKVDPHY101;
    }

    public void setModeKNDNKVDPHY101(float modeKNDNKVDPHY101) {
        this.modeKNDNKVDPHY101 = modeKNDNKVDPHY101;
    }

    public float getModeKNDNKNDPHY97() {
        return modeKNDNKNDPHY97;
    }

    public void setModeKNDNKNDPHY97(float modeKNDNKNDPHY97) {
        this.modeKNDNKNDPHY97 = modeKNDNKNDPHY97;
    }

    public float getModeKNDNKNDPHY99() {
        return modeKNDNKNDPHY99;
    }

    public void setModeKNDNKNDPHY99(float modeKNDNKNDPHY99) {
        this.modeKNDNKNDPHY99 = modeKNDNKNDPHY99;
    }

    public float getModeKNDNKNDPHY101() {
        return modeKNDNKNDPHY101;
    }

    public void setModeKNDNKNDPHY101(float modeKNDNKNDPHY101) {
        this.modeKNDNKNDPHY101 = modeKNDNKNDPHY101;
    }

    public float getModeKNDNKNDPLANE97() {
        return modeKNDNKNDPLANE97;
    }

    public void setModeKNDNKNDPLANE97(float modeKNDNKNDPLANE97) {
        this.modeKNDNKNDPLANE97 = modeKNDNKNDPLANE97;
    }

    public float getModeKNDNKNDPLANE99() {
        return modeKNDNKNDPLANE99;
    }

    public void setModeKNDNKNDPLANE99(float modeKNDNKNDPLANE99) {
        this.modeKNDNKNDPLANE99 = modeKNDNKNDPLANE99;
    }

    public float getModeKNDNKNDPLANE101() {
        return modeKNDNKNDPLANE101;
    }

    public void setModeKNDNKNDPLANE101(float modeKNDNKNDPLANE101) {
        this.modeKNDNKNDPLANE101 = modeKNDNKNDPLANE101;
    }

    public  int  getModeRotorVD() {
        return modeRotorVD;
    }
    public void setModeRotorVD(int modeRotorVD) {
        this.modeRotorVD = modeRotorVD;
    }
    public int  getModeRotorND() {
        return modeRotorND;
    }
    public void setModeRotorND(int modeRotorND) {
        this.modeRotorND = modeRotorND;
    }
    public int  getModeGeneratorTablo() {
        return modeGeneratorTablo;
    }
    public void setModeGeneratorTablo(int modeGeneratorTablo) {
        this.modeGeneratorTablo = modeGeneratorTablo;
    }

    /*
    public float getModeKVDHPCSpeed() {
        return modeKVDHPCSpeed;
    }

    public void setModeKVDHPCSpeed(float modeKVDHPCSpeed) {
        this.modeKVDHPCSpeed = modeKVDHPCSpeed;
    }

    public float getModeKNDHPCSpeed() {
        return modeKNDHPCSpeed;
    }

    public void setModeKNDHPCSpeed(float modeKNDHPCSpeed) {
        this.modeKNDHPCSpeed = modeKNDHPCSpeed;
    }

    public float getModeKNDHPCSpeedCited() {
        return modeKNDHPCSpeedCited;
    }

    public void setModeKNDHPCSpeedCited(float modeKNDHPCSpeedCited) {
        this.modeKNDHPCSpeedCited = modeKNDHPCSpeedCited;
    }

    public float getModeKNDHPCSpeedInstalled() {
        return modeKNDHPCSpeedInstalled;
    }

    public void setModeKNDHPCSpeedInstalled(float modeKNDHPCSpeedInstalled) {
        this.modeKNDHPCSpeedInstalled = modeKNDHPCSpeedInstalled;
    }
*/
    public float getModeWorkSum() {
        return modeWorkSum;
    }

    public void setModeWorkSum(float modeWorkSum) {
        this.modeWorkSum = modeWorkSum;
    }

    public float getModeWorkNom() {
        return modeWorkNom;
    }

    public void setModeWorkNom(float modeWorkNom) {
        this.modeWorkNom = modeWorkNom;
    }

    public float getModeWorkMax() {
        return modeWorkMax;
    }

    public void setModeWorkMax(float modeWorkMax) {
        this.modeWorkMax = modeWorkMax;
    }

    public int getModeWorkLaunchCount() {
        return modeWorkLaunchCount;
    }

    public void setModeWorkLaunchCount(int modeWorkLaunchCount) {
        this.modeWorkLaunchCount = modeWorkLaunchCount;
    }

    public int getModeWorkLaunchVSUCount() {
        return modeWorkLaunchVSUCount;
    }

    public void setModeWorkLaunchVSUCount(int modeWorkLaunchVSUCount) {
        this.modeWorkLaunchVSUCount = modeWorkLaunchVSUCount;
    }

    public int getModeWorkN1Count() {
        return modeWorkN1Count;
    }

    public void setModeWorkN1Count(int modeWorkN1Count) {
        this.modeWorkN1Count = modeWorkN1Count;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    private StepEngineData(Parcel in) {
        rowId = in.readString();

        engineName = in.readString();
        engineId = in.readInt();
        planeId = in.readInt();
        planeBoardId = in.readInt();
        launchDate = (java.util.Date) in.readSerializable();
        launchId = in.readString(); //WP no?
        atmPressure = in.readInt(); //Pnv
        atmTemp = in.readInt(); //Tnv

        work0Nominal = in.readFloat();
        workNominal = in.readFloat();
        workMax = in.readFloat();

        limb = in.readInt(); //По лимбу
        apuTime = in.readInt(); //Tзап ВСУ

        //launch
        launchEngineTemp = in.readFloat(); //T c двигателя заброс ???
        launchAPUOffEngine = in.readInt(); // n1 отключения ВСУ ???
        launchEngineTime = in.readInt(); // время запуска двигателя

        //small gas
        modeSmallGasHPCSpeed = in.readFloat(); //n1
        modeSmallGasTemp = in.readInt(); //Trc
        modeSmallGasOilPressure  = in.readFloat(); //Pm
        modeSmallGasOilTemp = in.readInt(); //TmC
        modeSmallGasFuelPressure= in.readInt(); //Pt
        modeSmallGasVibration= in.readInt(); //sqrt двигателя
        modeSmallGasHSPressure= in.readInt(); // основная р система
        modeSmallGasHSPressureEmergency= in.readInt(); //Аварийная при перекл.
        modeSmallGasBrakePressure= in.readInt(); //Pторм основное
        modeSmallGasBrakePressureStop= in.readInt(); //Pторм стояночное
        modeSmallGasGenerator= in.readInt(); //Vгенератора
        modeSmallGasPressureWing = in.readFloat(); //Крал баки
        modeSmallGasPressureHA = in.readFloat(); //г аккумулятора
        modeSmallGasPressureHT = in.readFloat(); //г бак
        modeSmallGasLightsClosure = in.readByte() != 0; //закрытие замков фонаря
        modeSmallGasConditioning = in.readByte() != 0; //включение кондиционирования
        modeSmallGasCabin   = in.readFloat(); //Pкабинsы

        //stage5
        stage5ModeName = in.readFloat(); //прямой ход
        stage5N1FlightTakeoff = in.readInt(); //полет - взлет
        stage5N1TakeoffLanding = in.readInt(); //взлет - посадка
        stage5N1FlightLanding = in.readInt(); //полет - посадка
        stage5N1Prc = in.readInt(); //prc
        stage5N1Release = in.readInt(); //выпуск
        stage5N1Cleaning = in.readInt(); //Уборка
        stage5N1BrakePrc = in.readInt();//Тормоз prc
        stage5N1Tmc = in.readInt(); //Tmc
        stage5N1VGenerator = in.readInt(); //Vгенератора

        //stage3
        stage3ModeName = in.readInt(); //прямой ход

        //0.85 Nom
        mode85NomHPCSpeed = in.readFloat(); //n1
        mode85NomTemp = in.readInt();
        mode85NomOilPressure = in.readFloat();
        mode85NomOilTemp = in.readInt();
        mode85NomFuelPressure = in.readInt();
        mode85NomVibration = in.readInt();

        //n=100
        mode100Vibration = in.readInt();

        //Nom
        modeNomHPCSpeed = in.readFloat(); //n1
        modeNomTemp = in.readInt();
        modeNomOilPressure = in.readFloat();
        modeNomOilTemp = in.readInt();
        modeNomFuelPressure = in.readInt();
        modeNomVibration = in.readInt();
        modeNom4001OnSignal = in.readByte() != 0;
        modeNom4001OnEngine = in.readByte() != 0;
        modeNom4001OffSignal = in.readByte() != 0;
        modeNom4001OffEngine = in.readByte() != 0;
        modeNomPTBKHeat = in.readByte() != 0;
        modeNomPTBKCold = in.readByte() != 0;
        modeNomPTBKAutomation = in.readByte() != 0;
        modeNomShowerHeat = in.readByte() != 0;
        modeNomShowerCold = in.readByte() != 0;
        modeNomShowerAutomation = in.readByte() != 0;
        modeNomVGenerator = in.readInt();

        //Max
        modeMaxHPCSpeed = in.readFloat();
        modeMaxHPCSpeedN2 = in.readFloat();
        modeMaxTemp = in.readInt();
        modeMaxOilPressure = in.readFloat();
        modeMaxOilTemp = in.readInt();
        modeMaxFuelPressure = in.readInt();
        modeMaxVibration = in.readInt();
        modeMaxVGenerator = in.readInt();
        modeMaxGeneratorSpeedConst = in.readByte() != 0;
        modeMaxPressureCabin = in.readFloat();
        modeMaxPressureWings = in.readFloat();
        modeMaxPressureNozzles = in.readInt();

        //stageN
        stageN3ModeName = in.readInt();
        stageN5ModeName = in.readInt();

        //acceleration
        modeAccelerationIdleMaxIn = in.readFloat();
        modeAccelerationIdleOut = in.readFloat();

        //mode Small Gas 2
        modeSmallGas2HPCSpeed = in.readFloat(); //n1
        modeSmallGas2Temp = in.readInt(); //Trc
        modeSmallGas2OilPressure = in.readFloat(); //Pm
        modeSmallGas2OilTemp = in.readInt(); //TmC
        modeSmallGas2FuelPressure = in.readInt(); //Pt
        modeSmallGas2Vibration = in.readInt(); //sqrt двигателя
        modeSmallGas2SwitchMin = in.readFloat();
        modeSmallGas2SwitchMax = in.readFloat();
        modeSmallGas2CondOn = in.readFloat();
        modeSmallGas2CondOff = in.readFloat();
        modeSmallGas2AntifreezeOn = in.readFloat();
        modeSmallGas2AntifreezeOff = in.readFloat();
        //modeSmallGas2CondOn = in.readByte() != 0;
        //modeSmallGas2CondOff = in.readByte() != 0;
        //modeSmallGas2AntifreezeOn = in.readByte() != 0;
        //modeSmallGas2AntifreezeOff = in.readByte() != 0;

        //mode KND
        //modeKVDHPCSpeed = in.readFloat();
        //modeKNDHPCSpeed = in.readFloat();
        //modeKNDHPCSpeedCited = in.readFloat();
        //modeKNDHPCSpeedInstalled = in.readFloat();
       modeKNDNKVDPHY97 = in.readFloat();
       modeKNDNKVDPHY99 = in.readFloat();
       modeKNDNKVDPHY101 = in.readFloat();
       modeKNDNKNDPHY97 = in.readFloat();
       modeKNDNKNDPHY99 = in.readFloat();
       modeKNDNKNDPHY101 = in.readFloat();
       modeKNDNKNDPLANE97 = in.readFloat();
       modeKNDNKNDPLANE99 = in.readFloat();
       modeKNDNKNDPLANE101 = in.readFloat();

        //rotors
        modeRotorVD = in.readInt();
        modeRotorND = in.readInt();

        //Generator tablo
        modeGeneratorTablo = in.readInt();

        //mode Work
        modeWorkSum = in.readFloat();
        modeWorkNom = in.readFloat();
        modeWorkMax = in.readFloat();
        modeWorkLaunchCount = in.readInt();
        modeWorkLaunchVSUCount = in.readInt();
        modeWorkN1Count = in.readInt();

        currentUserId = in.readString();
    }

    public static final Creator<StepEngineData> CREATOR = new Creator<StepEngineData>() {
        @Override
        public StepEngineData createFromParcel(Parcel in) {
            return new StepEngineData(in);
        }

        @Override
        public StepEngineData[] newArray(int size) {
            return new StepEngineData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rowId);
        dest.writeString(engineName);
        dest.writeInt(engineId);
        dest.writeInt(planeId);
        dest.writeInt(planeBoardId);
        dest.writeSerializable(launchDate);
        //dest.writeString(email);

        dest.writeString(launchId);
        dest.writeInt(atmPressure);
        dest.writeInt(atmTemp);

        dest.writeFloat(work0Nominal);
        dest.writeFloat(workNominal);
        dest.writeFloat(workMax);

        dest.writeInt(limb);
        dest.writeInt(apuTime);

        //launch
        dest.writeFloat(launchEngineTemp);
        dest.writeInt(launchAPUOffEngine);
        dest.writeInt(launchEngineTime);

        //small gas
        dest.writeFloat(modeSmallGasHPCSpeed);
        dest.writeInt(modeSmallGasTemp);
        dest.writeFloat(modeSmallGasOilPressure);
        dest.writeInt(modeSmallGasOilTemp);
        dest.writeInt(modeSmallGasFuelPressure);
        dest.writeInt(modeSmallGasVibration);
        dest.writeInt(modeSmallGasHSPressure);
        dest.writeInt(modeSmallGasHSPressureEmergency);
        dest.writeInt(modeSmallGasBrakePressure);
        dest.writeInt(modeSmallGasBrakePressureStop);
        dest.writeInt(modeSmallGasGenerator);
        dest.writeFloat(modeSmallGasPressureWing);
        dest.writeFloat(modeSmallGasPressureHA);
        dest.writeFloat(modeSmallGasPressureHT);
        dest.writeByte((byte) (modeSmallGasLightsClosure ? 1 : 0));
        dest.writeByte((byte) (modeSmallGasConditioning ? 1 : 0));
        dest.writeFloat(modeSmallGasCabin);

        //stage5
        dest.writeFloat(stage5ModeName);
        dest.writeInt(stage5N1FlightTakeoff);
        dest.writeInt(stage5N1TakeoffLanding);
        dest.writeInt(stage5N1FlightLanding);
        dest.writeInt(stage5N1Prc);
        dest.writeInt(stage5N1Release);
        dest.writeInt(stage5N1Cleaning);
        dest.writeInt(stage5N1BrakePrc);
        dest.writeInt(stage5N1Tmc);
        dest.writeInt(stage5N1VGenerator);

        //stage3
        dest.writeInt(stage3ModeName);

        //0.85 Nom
        dest.writeFloat(mode85NomHPCSpeed);
        dest.writeInt(mode85NomTemp);
        dest.writeFloat(mode85NomOilPressure);
        dest.writeInt(mode85NomOilTemp);
        dest.writeInt(mode85NomFuelPressure);
        dest.writeInt(mode85NomVibration);

        //n=100
        dest.writeInt(mode100Vibration);

        //Nom
        dest.writeFloat(modeNomHPCSpeed);
        dest.writeInt(modeNomTemp);
        dest.writeFloat(modeNomOilPressure);
        dest.writeInt(modeNomOilTemp);
        dest.writeInt(modeNomFuelPressure);
        dest.writeInt(modeNomVibration);
        dest.writeByte((byte) (modeNom4001OnSignal ? 1 : 0));
        dest.writeByte((byte) (modeNom4001OnEngine ? 1 : 0));
        dest.writeByte((byte) (modeNom4001OffSignal ? 1 : 0));
        dest.writeByte((byte) (modeNom4001OffEngine ? 1 : 0));
        dest.writeByte((byte) (modeNomPTBKHeat ? 1 : 0));
        dest.writeByte((byte) (modeNomPTBKCold ? 1 : 0));
        dest.writeByte((byte) (modeNomPTBKAutomation ? 1 : 0));
        dest.writeByte((byte) (modeNomShowerHeat ? 1 : 0));
        dest.writeByte((byte) (modeNomShowerCold ? 1 : 0));
        dest.writeByte((byte) (modeNomShowerAutomation ? 1 : 0));
        dest.writeInt(modeNomVGenerator);

        //Max
        dest.writeFloat(modeMaxHPCSpeed);
        dest.writeFloat(modeMaxHPCSpeedN2);
        dest.writeInt(modeMaxTemp);
        dest.writeFloat(modeMaxOilPressure);
        dest.writeInt(modeMaxOilTemp);
        dest.writeInt(modeMaxFuelPressure);
        dest.writeInt(modeMaxVibration);
        dest.writeInt(modeMaxVGenerator);
        dest.writeByte((byte) (modeMaxGeneratorSpeedConst ? 1 : 0));
        dest.writeFloat(modeMaxPressureCabin);
        dest.writeFloat(modeMaxPressureWings);
        dest.writeInt(modeMaxPressureNozzles);

        //stageN
        dest.writeInt(stageN3ModeName);
        dest.writeInt(stageN5ModeName);

        //acceleration
        dest.writeFloat(modeAccelerationIdleMaxIn);
        dest.writeFloat(modeAccelerationIdleOut);

        //mode Small Gas 2
        dest.writeFloat(modeSmallGas2HPCSpeed);
        dest.writeInt(modeSmallGas2Temp);
        dest.writeFloat(modeSmallGas2OilPressure);
        dest.writeInt(modeSmallGas2OilTemp);
        dest.writeInt(modeSmallGas2FuelPressure);
        dest.writeInt(modeSmallGas2Vibration);
        dest.writeFloat(modeSmallGas2SwitchMin);
        dest.writeFloat(modeSmallGas2SwitchMax);
        dest.writeFloat(modeSmallGas2CondOn);
        dest.writeFloat(modeSmallGas2CondOff);
        dest.writeFloat(modeSmallGas2AntifreezeOn);
        dest.writeFloat(modeSmallGas2AntifreezeOff);

        //dest.writeByte((byte) (modeSmallGas2CondOn ? 1 : 0));
        //dest.writeByte((byte) (modeSmallGas2CondOff ? 1 : 0));
        //dest.writeByte((byte) (modeSmallGas2AntifreezeOn ? 1 : 0));
        //dest.writeByte((byte) (modeSmallGas2AntifreezeOff ? 1 : 0));

        //mode KND
        //dest.writeFloat(modeKVDHPCSpeed);
        //dest.writeFloat(modeKNDHPCSpeed);
        //dest.writeFloat(modeKNDHPCSpeedCited);
        //dest.writeFloat(modeKNDHPCSpeedInstalled);
        dest.writeFloat(modeKNDNKVDPHY97);
        dest.writeFloat(modeKNDNKVDPHY99);
        dest.writeFloat(modeKNDNKVDPHY101);
        dest.writeFloat(modeKNDNKNDPHY97);
        dest.writeFloat(modeKNDNKNDPHY99);
        dest.writeFloat(modeKNDNKNDPHY101);
        dest.writeFloat(modeKNDNKNDPLANE97);
        dest.writeFloat(modeKNDNKNDPLANE99);
        dest.writeFloat(modeKNDNKNDPLANE101);

        //rotors
        dest.writeInt(modeRotorVD);
        dest.writeInt(modeRotorND);

        //Generator tablo
        dest.writeInt(modeGeneratorTablo);

        //mode Work
        dest.writeFloat(modeWorkSum);
        dest.writeFloat(modeWorkNom);
        dest.writeFloat(modeWorkMax);
        dest.writeInt(modeWorkLaunchCount);
        dest.writeInt(modeWorkLaunchVSUCount);
        dest.writeInt(modeWorkN1Count);

        dest.writeString(currentUserId);

    }
}
