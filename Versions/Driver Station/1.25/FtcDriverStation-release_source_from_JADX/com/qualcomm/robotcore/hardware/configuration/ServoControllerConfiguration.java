package com.qualcomm.robotcore.hardware.configuration;

import com.qualcomm.robotcore.BuildConfig;
import com.qualcomm.robotcore.hardware.configuration.DeviceConfiguration.ConfigurationType;
import com.qualcomm.robotcore.util.SerialNumber;
import java.util.ArrayList;
import java.util.List;

public class ServoControllerConfiguration extends ControllerConfiguration {
    public ServoControllerConfiguration() {
        super(BuildConfig.VERSION_NAME, new ArrayList(), new SerialNumber(ControllerConfiguration.NO_SERIAL_NUMBER.getSerialNumber()), ConfigurationType.SERVO_CONTROLLER);
    }

    public ServoControllerConfiguration(String name, List<DeviceConfiguration> servos, SerialNumber serialNumber) {
        super(name, servos, serialNumber, ConfigurationType.SERVO_CONTROLLER);
    }

    public List<DeviceConfiguration> getServos() {
        return super.getDevices();
    }

    public void addServos(ArrayList<DeviceConfiguration> servos) {
        super.addDevices(servos);
    }
}
