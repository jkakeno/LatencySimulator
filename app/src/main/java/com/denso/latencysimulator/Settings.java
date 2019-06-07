package com.denso.latencysimulator;

import android.os.Parcel;
import android.os.Parcelable;

public class Settings implements Parcelable {

    /* Default values
            remoteCmd(0,1000);
            postCmd(1000,1500);
            postAck(2000,2500);
            specD(4500,5000);
            callbackPhase1(4500,5000);
            sms(10000,15000);
            smsAck1(15000,17000);
            smsAck2(17000,17500);
            callbackPhase2(17500,18000);
            accessTSC(21000,22000);
            downloadCmd(22000,25000);
            callbackPhase3(26000,26500);
            uploadResult(32000,33000);
            callbackPhase4(36000,37000);
            remoteCmdResponse(37000,38000);
    */

    long remoteCmdStart=0;
    long remoteCmdDuration =1000;
    long postCmdStart;
    long postCmdDuration =1500;
//    long postAckStart;
//    long postAckDuration =2500;
    long specDStart;
    long specDDuration =5000;
//    long callbackPhase1Start;
//    long callbackPhase1End=5000;
    long smsStart;
    long smsDuration =15000;
//    long smsAck1Start;
//    long smsAck1End=17000;
//    long smsAck2Start;
//    long smsAck2End=17500;
//    long callbackPhase2Start;
//    long callbackPhase2End=18000;
    long accessTSCStart;
    long accessTSCDuration =22000;
    long downloadCmdStart;
    long downloadCmdDuration =25000;
//    long callbackPhase3Start;
//    long callbackPhase3End=26500;
    long uploadResultStart;
    long uploadResultDuration =33000;
    long callbackPhase4Start;
    long callbackPhase4Duration =37000;
    long remoteCmdResponseStart;
    long remoteCmdResponseDuration =38000;

    long remoteCmdWait=0;
    long postCmdWait=0;
//    long postAckWait=0;
    long specDWait=0;
    long callbackPhase1Wait=0;
    long smsWait=0;
//    long smsAck1Wait=0;
//    long smsAck2Wait=0;
//    long callbackPhase2Wait=0;
    long accessTSCWait=0;
    long downloadCmdWait=0;
//    long callbackPhase3Wait=0;
    long uploadResultWait=0;
    long callbackPhase4Wait=0;
    long remoteCmdResponseWait=0;

    public Settings() {
    }


    protected Settings(Parcel in) {
        remoteCmdStart = in.readLong();
        remoteCmdDuration = in.readLong();
        postCmdStart = in.readLong();
        postCmdDuration = in.readLong();
        specDStart = in.readLong();
        specDDuration = in.readLong();
        smsStart = in.readLong();
        smsDuration = in.readLong();
        accessTSCStart = in.readLong();
        accessTSCDuration = in.readLong();
        downloadCmdStart = in.readLong();
        downloadCmdDuration = in.readLong();
        uploadResultStart = in.readLong();
        uploadResultDuration = in.readLong();
        callbackPhase4Start = in.readLong();
        callbackPhase4Duration = in.readLong();
        remoteCmdResponseStart = in.readLong();
        remoteCmdResponseDuration = in.readLong();
        remoteCmdWait = in.readLong();
        postCmdWait = in.readLong();
        specDWait = in.readLong();
        callbackPhase1Wait = in.readLong();
        smsWait = in.readLong();
        accessTSCWait = in.readLong();
        downloadCmdWait = in.readLong();
        uploadResultWait = in.readLong();
        callbackPhase4Wait = in.readLong();
        remoteCmdResponseWait = in.readLong();
    }

    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    public long getRemoteCmdStart() {
        return remoteCmdStart;
    }

//    public void setRemoteCmdStart(long remoteCmdStart) {
//        this.remoteCmdStart = remoteCmdStart;
//    }

    public long getRemoteCmdDuration() {
        return remoteCmdDuration;
    }

    public void setRemoteCmdDuration(long remoteCmdDuration) {
        this.remoteCmdDuration = remoteCmdDuration;
    }

    public long getPostCmdStart() {
        return postCmdStart = remoteCmdDuration + postCmdWait;
    }

//    public void setPostCmdStart(long postCmdStart) {
//        this.postCmdStart = postCmdStart;
//    }

    public long getPostCmdDuration() {
        return postCmdDuration + remoteCmdDuration;
    }

    public void setPostCmdDuration(long postCmdDuration) {
        this.postCmdDuration = postCmdDuration;
    }

//    public long getPostAckStart() {
//        return postAckStart = postCmdStart + postCmdDuration + postAckWait;
//    }

//    public void setPostAckStart(long postAckStart) {
//        this.postAckStart = postAckStart;
//    }

//    public long getPostAckDuration() {
//        return postAckDuration;
//    }
//
//    public void setPostAckDuration(long postAckDuration) {
//        this.postAckDuration = postAckDuration;
//    }
//
//    public long getSpecDStart() {
//        return specDStart = postAckStart + specDWait;
//    }

//    public void setSpecDStart(long specDStart) {
//        this.specDStart = specDStart;
//    }

    public long getSpecDDuration() {
        return specDDuration;
    }

    public void setSpecDDuration(long specDDuration) {
        this.specDDuration = specDDuration;
    }

//    public long getCallbackPhase1Start() {
//        return callbackPhase1Start = specDStart + callbackPhase1Wait;
//    }

//    public void setCallbackPhase1Start(long callbackPhase1Start) {
//        this.callbackPhase1Start = callbackPhase1Start;
//    }

//    public long getCallbackPhase1End() {
//        return callbackPhase1End;
//    }
//
//    public void setCallbackPhase1End(long callbackPhase1End) {
//        this.callbackPhase1End = callbackPhase1End;
//    }

    public long getSmsStart() {
        return smsStart = specDStart + specDDuration + smsWait;
    }

//    public void setSmsStart(long smsStart) {
//        this.smsStart = smsStart;
//    }

    public long getSmsDuration() {
        return smsDuration;
    }

    public void setSmsDuration(long smsDuration) {
        this.smsDuration = smsDuration;
    }

//    public long getSmsAck1Start() {
//        return smsAck1Start = smsStart + smsDuration + smsAck1Wait;
//    }

//    public void setSmsAck1Start(long smsAck1Start) {
//        this.smsAck1Start = smsAck1Start;
//    }

//    public long getSmsAck1End() {
//        return smsAck1End;
//    }
//
//    public void setSmsAck1End(long smsAck1End) {
//        this.smsAck1End = smsAck1End;
//    }
//
//    public long getSmsAck2Start() {
//        return smsAck2Start = smsAck1Start + smsAck1End + smsAck2Wait;
//    }

//    public void setSmsAck2Start(long smsAck2Start) {
//        this.smsAck2Start = smsAck2Start;
//    }

//    public long getSmsAck2End() {
//        return smsAck2End;
//    }
//
//    public void setSmsAck2End(long smsAck2End) {
//        this.smsAck2End = smsAck2End;
//    }
//
//    public long getCallbackPhase2Start() {
//        return callbackPhase2Start = smsAck2Start + smsAck2End + callbackPhase2Wait;
//    }

//    public void setCallbackPhase2Start(long callbackPhase2Start) {
//        this.callbackPhase2Start = callbackPhase2Start;
//    }

//    public long getCallbackPhase2End() {
//        return callbackPhase2End;
//    }
//
//    public void setCallbackPhase2End(long callbackPhase2End) {
//        this.callbackPhase2End = callbackPhase2End;
//    }
//
//    public long getAccessTSCStart() {
//        return accessTSCStart = smsAck1Start + accessTSCWait;
//    }

//    public void setAccessTSCStart(long accessTSCStart) {
//        this.accessTSCStart = accessTSCStart;
//    }

    public long getAccessTSCDuration() {
        return accessTSCDuration;
    }

    public void setAccessTSCDuration(long accessTSCDuration) {
        this.accessTSCDuration = accessTSCDuration;
    }

    public long getDownloadCmdStart() {
        return downloadCmdStart = accessTSCStart + accessTSCDuration + downloadCmdWait;
    }

//    public void setDownloadCmdStart(long downloadCmdStart) {
//        this.downloadCmdStart = downloadCmdStart;
//    }

    public long getDownloadCmdDuration() {
        return downloadCmdDuration;
    }

    public void setDownloadCmdDuration(long downloadCmdDuration) {
        this.downloadCmdDuration = downloadCmdDuration;
    }

//    public long getCallbackPhase3Start() {
//        return callbackPhase3Start = downloadCmdStart + callbackPhase3Wait;
//    }

//    public void setCallbackPhase3Start(long callbackPhase3Start) {
//        this.callbackPhase3Start = callbackPhase3Start;
//    }

//    public long getCallbackPhase3End() {
//        return callbackPhase3End;
//    }
//
//    public void setCallbackPhase3End(long callbackPhase3End) {
//        this.callbackPhase3End = callbackPhase3End;
//    }

    public long getUploadResultStart() {
        return uploadResultStart = downloadCmdStart + downloadCmdDuration + uploadResultWait;
    }

//    public void setUploadResultStart(long uploadResultStart) {
//        this.uploadResultStart = uploadResultStart;
//    }

    public long getUploadResultDuration() {
        return uploadResultDuration;
    }

    public void setUploadResultDuration(long uploadResultDuration) {
        this.uploadResultDuration = uploadResultDuration;
    }

    public long getCallbackPhase4Start() {
        return callbackPhase4Start = uploadResultStart + uploadResultDuration + callbackPhase4Wait;
    }

//    public void setCallbackPhase4Start(long callbackPhase4Start) {
//        this.callbackPhase4Start = callbackPhase4Start;
//    }

    public long getCallbackPhase4Duration() {
        return callbackPhase4Duration;
    }

    public void setCallbackPhase4Duration(long callbackPhase4Duration) {
        this.callbackPhase4Duration = callbackPhase4Duration;
    }

    public long getRemoteCmdResponseStart() {
        return remoteCmdResponseStart = callbackPhase4Start + callbackPhase4Duration + remoteCmdResponseWait;
    }

//    public void setRemoteCmdResponseStart(long remoteCmdResponseStart) {
//        this.remoteCmdResponseStart = remoteCmdResponseStart;
//    }

    public long getRemoteCmdResponseDuration() {
        return remoteCmdResponseDuration;
    }

    public void setRemoteCmdResponseDuration(long remoteCmdResponseDuration) {
        this.remoteCmdResponseDuration = remoteCmdResponseDuration;
    }

    public long getRemoteCmdWait() {
        return remoteCmdWait;
    }

    public void setRemoteCmdWait(long remoteCmdWait) {
        this.remoteCmdWait = remoteCmdWait;
    }

    public long getPostCmdWait() {
        return postCmdWait;
    }

    public void setPostCmdWait(long postCmdWait) {
        this.postCmdWait = postCmdWait;
    }

//    public long getPostAckWait() {
//        return postAckWait;
//    }
//
//    public void setPostAckWait(long postAckWait) {
//        this.postAckWait = postAckWait;
//    }

    public long getSpecDWait() {
        return specDWait;
    }

    public void setSpecDWait(long specDWait) {
        this.specDWait = specDWait;
    }

    public long getCallbackPhase1Wait() {
        return callbackPhase1Wait;
    }

    public void setCallbackPhase1Wait(long callbackPhase1Wait) {
        this.callbackPhase1Wait = callbackPhase1Wait;
    }

    public long getSmsWait() {
        return smsWait;
    }

    public void setSmsWait(long smsWait) {
        this.smsWait = smsWait;
    }

//    public long getSmsAck1Wait() {
//        return smsAck1Wait;
//    }
//
//    public void setSmsAck1Wait(long smsAck1Wait) {
//        this.smsAck1Wait = smsAck1Wait;
//    }
//
//    public long getSmsAck2Wait() {
//        return smsAck2Wait;
//    }
//
//    public void setSmsAck2Wait(long smsAck2Wait) {
//        this.smsAck2Wait = smsAck2Wait;
//    }
//
//    public long getCallbackPhase2Wait() {
//        return callbackPhase2Wait;
//    }
//
//    public void setCallbackPhase2Wait(long callbackPhase2Wait) {
//        this.callbackPhase2Wait = callbackPhase2Wait;
//    }

    public long getAccessTSCWait() {
        return accessTSCWait;
    }

    public void setAccessTSCWait(long accessTSCWait) {
        this.accessTSCWait = accessTSCWait;
    }

    public long getDownloadCmdWait() {
        return downloadCmdWait;
    }

    public void setDownloadCmdWait(long downloadCmdWait) {
        this.downloadCmdWait = downloadCmdWait;
    }

//    public long getCallbackPhase3Wait() {
//        return callbackPhase3Wait;
//    }
//
//    public void setCallbackPhase3Wait(long callbackPhase3Wait) {
//        this.callbackPhase3Wait = callbackPhase3Wait;
//    }

    public long getUploadResultWait() {
        return uploadResultWait;
    }

    public void setUploadResultWait(long uploadResultWait) {
        this.uploadResultWait = uploadResultWait;
    }

    public long getCallbackPhase4Wait() {
        return callbackPhase4Wait;
    }

    public void setCallbackPhase4Wait(long callbackPhase4Wait) {
        this.callbackPhase4Wait = callbackPhase4Wait;
    }

    public long getRemoteCmdResponseWait() {
        return remoteCmdResponseWait;
    }

    public void setRemoteCmdResponseWait(long remoteCmdResponseWait) {
        this.remoteCmdResponseWait = remoteCmdResponseWait;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(remoteCmdStart);
        dest.writeLong(remoteCmdDuration);
        dest.writeLong(postCmdStart);
        dest.writeLong(postCmdDuration);
        dest.writeLong(specDStart);
        dest.writeLong(specDDuration);
        dest.writeLong(smsStart);
        dest.writeLong(smsDuration);
        dest.writeLong(accessTSCStart);
        dest.writeLong(accessTSCDuration);
        dest.writeLong(downloadCmdStart);
        dest.writeLong(downloadCmdDuration);
        dest.writeLong(uploadResultStart);
        dest.writeLong(uploadResultDuration);
        dest.writeLong(callbackPhase4Start);
        dest.writeLong(callbackPhase4Duration);
        dest.writeLong(remoteCmdResponseStart);
        dest.writeLong(remoteCmdResponseDuration);
        dest.writeLong(remoteCmdWait);
        dest.writeLong(postCmdWait);
        dest.writeLong(specDWait);
        dest.writeLong(callbackPhase1Wait);
        dest.writeLong(smsWait);
        dest.writeLong(accessTSCWait);
        dest.writeLong(downloadCmdWait);
        dest.writeLong(uploadResultWait);
        dest.writeLong(callbackPhase4Wait);
        dest.writeLong(remoteCmdResponseWait);
    }
}
