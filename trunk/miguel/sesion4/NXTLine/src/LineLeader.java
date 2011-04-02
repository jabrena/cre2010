import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;

public class LineLeader extends I2CSensor {
   byte[] buf = new byte[8];

   /**
    * Used by calibrateSensor() to calibrate the white level
    */
   public static final char WHITE = 'W';

   /**
    * Used by calibrateSensor() to calibrate the black level
    */
   public static final char BLACK = 'B';

   /**
    * Used by setSleep() to put sensor to sleep
    */
   public static final char SLEEP = 'D';

   /**
    * Used by setSleep() to wake the sensor up
    */
   public static final char WAKE = 'P';

   /**
    * Used by setLineColor() to reset the color inversion (black line on a
    * white background, this is also the default).
    */
   public static final char NORMAL = 'R';

   /**
    * Used by setLineColor() to invert the colors (White line on a black
    * background)
    */
   public static final char INVERTED = 'I';

   /**
    * Used by setElectricalFrequency() to configure the sensor for US region
    * (and regions with 60 Hz electrical frequency).
    */
   public static final char US = 'A';

   /**
    * Used by setElectricalFrequency() to configure the sensor for European
    * region (and regions with 50 Hz electrical frequency)
    */
   public static final char EU = 'E';

   /**
    * Used by setElectricalFrequency() to configure the sensor for universal
    * frequency (in this mode the sensor adjusts for any frequency, this is
    * also the default mode).
    */
   public static final char U = 'U';

   public LineLeader(I2CPort port) {
      super(port);
   }

   /**
    * Calibrate the sensors white and black values
    *
    * @param calibration
    *            Use the class constants WHITE or BLACK.
    */
   public void calibrateSensor(char calibration) {
      sendCommand(calibration);
   }

   /**
    * To conserve power, the sensor goes to sleep after 1 minute of inactivity.
    * The sensor will wake up on its own when any activity begins. You can also
    * wake it up (or put to sleep) via this command.
    *
    * @param sleep
    *            Use the class constants SLEEP or WAKE.
    */
   public void setSleep(char sleep) {
      sendCommand(sleep);
   }

   /**
    * The sensor can be use with a black or white line on opposite backgrounds,
    * the default is a black line on a white background, use this command to
    * set the line color.
    *
    * @param color
    *            Use the class constants NORMAL or INVERTED.
    */
   public void setLineColor(char color) {
      sendCommand(color);
   }

   /**
    * The sensor can be adjusted for different electrical frequency the default
    * is universal frequency mode, use this command to the electrical
    * frequency.
    *
    * @param color
    *            Use the class constants US ,EU or U.
    */
   public void setElectricalFrequency(char area) {
      sendCommand(area);
   }

   /**
    * Take a snapshot, this command looks at the line under the sensor and
    * stores the width and position of the line in sensorâ€™s memory.
    * Subsequently, sensor will use these characteristics of line to track it.
    * This command inverts the colors if it sees a white line on black
    * background. (PID parameters are not affected).
    */
   public void takeSnapshot() {
      sendCommand('S');
   }

   /**
    *
    * In the simplest form, the sensor will try to maintain line at the center
    * of the sensor. In order to do so, your robot may have to apply different
    * power to left or right motors. This value is provided as the correction
    * needed. You can add this value to your left motor and subtract from right
    * motor. Alternately, you can perform other operations with this value,
    * based on your robotâ€™s and courseâ€™s needs.
    *
    * @return the steering value
    */
   public int getSteering() {
      int ret = getData(0x42, buf, 1);
      if (ret != 0)
         return -1;
      return buf[0];
   }

   /**
    * The average is a weighted average of the bits set to 1 based on the
    * position. i.e. left most bit has weight of 10, second bit has weight of
    * 20.
    *
    * @return the average
    */
   public int getAverage() {
      int ret = getData(0x43, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   /**
    * Result is a byte value of the sensor reading. Each bit corresponding to
    * the sensor where the line is seen is set to 1, or else the bit is zero.
    *
    * @return the result
    */
   public int getResult() {
      int ret = getData(0x44, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   /**
    * The Set Point is a value you can ask sensor to maintain the average to.
    * The default value is 45, whereby the line is maintained in center of the
    * sensor. If you need to maintain line towards left of the sensor, set the
    * Set Point to a lower value (minimum: 10). If you need it to be towards on
    * the right of the sensor, set it to higher value (maximum: 80). Set point
    * is also useful while tracking an edge of dark and light areas.
    *
    * @return the set point
    */
   public int getSetPoint() {
      int ret = getData(0x45, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKp() {
      int ret = getData(0x46, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKi() {
      int ret = getData(0x47, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKd() {
      int ret = getData(0x48, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKpDivisor() {
      int ret = getData(0x61, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKiDivisor() {
      int ret = getData(0x62, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getKdDivisor() {
      int ret = getData(0x63, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int setSetPoint(int point) {
      return sendData(0x45, (byte) point);
   }

   public int setKp(int kp) {
      return sendData(0x46, (byte) kp);
   }

   public int setKi(int ki) {
      return sendData(0x47, (byte) ki);
   }

   public int setKd(int kd) {
      return sendData(0x48, (byte) kd);
   }

   public int setKpDivisor(int divisor) {
      return sendData(0x61, (byte) divisor);
   }

   public int setKiDivisor(int divisor) {
      return sendData(0x62, (byte) divisor);
   }

   public int setKdDivisor(int divisor) {
      return sendData(0x63, (byte) divisor);
   }

   public int getSensorValue(int id) {
      if (id <= 0 || id > 8)
         return -1;
      int ret = getData(0x48 + id, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getWhiteLimit(int id) {
      if (id <= 0 || id > 8)
         return -1;
      int ret = getData(0x50 + id, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   public int getBlackLimit(int id) {
      if (id <= 0 || id > 8)
         return -1;
      int ret = getData(0x58 + id, buf, 1);
      if (ret != 0)
         return -1;
      return (0xFF & buf[0]);
   }

   /**
    * Send a single byte command represented by a letter
    *
    * @param cmd
    *            the letter that identifies the command
    */
   public void sendCommand(char cmd) {
      sendData(0x41, (byte) cmd);
   }

} 
