package com.qualcomm.robotcore.util;

import com.qualcomm.robotcore.BuildConfig;
import com.qualcomm.robotcore.exception.RobotCoreException;

public class RunShellCommand {
    boolean f576a;

    public RunShellCommand() {
        this.f576a = false;
    }

    public void enableLogging(boolean enable) {
        this.f576a = enable;
    }

    public String run(String cmd) {
        if (this.f576a) {
            RobotLog.m330v("running command: " + cmd);
        }
        String a = m332a(cmd, false);
        if (this.f576a) {
            RobotLog.m330v("         output: " + a);
        }
        return a;
    }

    public String runAsRoot(String cmd) {
        if (this.f576a) {
            RobotLog.m330v("running command: " + cmd);
        }
        String a = m332a(cmd, true);
        if (this.f576a) {
            RobotLog.m330v("         output: " + a);
        }
        return a;
    }

    private String m332a(String str, boolean z) {
        byte[] bArr = new byte[524288];
        String str2 = BuildConfig.VERSION_NAME;
        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
        Process process = null;
        if (z) {
            try {
                processBuilder.command(new String[]{"su", "-c", str}).redirectErrorStream(true);
            } catch (Exception e) {
                RobotLog.logStacktrace(e);
                if (process != null) {
                    process.destroy();
                }
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                if (process != null) {
                    process.destroy();
                }
            } catch (Throwable th) {
                if (process != null) {
                    process.destroy();
                }
            }
        } else {
            processBuilder.command(new String[]{"sh", "-c", str}).redirectErrorStream(true);
        }
        process = processBuilder.start();
        process.waitFor();
        int read = process.getInputStream().read(bArr);
        if (read > 0) {
            str2 = new String(bArr, 0, read);
        }
        if (process != null) {
            process.destroy();
        }
        return str2;
    }

    public static void killSpawnedProcess(String processName, String packageName, RunShellCommand shell) throws RobotCoreException {
        try {
            int spawnedProcessPid = getSpawnedProcessPid(processName, packageName, shell);
            while (spawnedProcessPid != -1) {
                RobotLog.m330v("Killing PID " + spawnedProcessPid);
                shell.run(String.format("kill %d", new Object[]{Integer.valueOf(spawnedProcessPid)}));
                spawnedProcessPid = getSpawnedProcessPid(processName, packageName, shell);
            }
        } catch (Exception e) {
            throw new RobotCoreException(String.format("Failed to kill %s instances started by this app", new Object[]{processName}));
        }
    }

    public static int getSpawnedProcessPid(String processName, String packageName, RunShellCommand shell) {
        int i = 0;
        String run = shell.run("ps");
        CharSequence charSequence = "invalid";
        for (String str : run.split("\n")) {
            if (str.contains(packageName)) {
                charSequence = str.split("\\s+")[0];
                break;
            }
        }
        String[] split = run.split("\n");
        int length = split.length;
        while (i < length) {
            String str2 = split[i];
            if (str2.contains(processName) && str2.contains(r0)) {
                return Integer.parseInt(str2.split("\\s+")[1]);
            }
            i++;
        }
        return -1;
    }
}
