package utils;
//eingegebene Daten aus der Maske
public class DataSet {

    private String userName = "";
    private String password = "";
    private String hostUrl = "";
    private String taskName = "";

    private static boolean canceled = true;

    private static DataSet dataSet = null;

    private DataSet() {}

    public static DataSet getInstance() {
        if (dataSet == null) {
            dataSet = new DataSet();
            return dataSet;
        } else {
            return dataSet;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static boolean isCanceled() {
        return canceled;
    }

    public static void setCanceled(boolean cancel) {
        canceled = cancel;
    }
}
