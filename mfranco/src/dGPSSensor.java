import lejos.nxt.*;
import lejos.util.*;

import lejos.nxt.comm.RConsole;

/** 
 * Class for controlling dGPS sensor from Dexter Industries
 * 
 * @author Mark Crosbie  <mark@mastincrosbie.com>
 * 22 January, 2011
 * 
 */ 
class dGPSSensor extends I2CSensor { 
        
    public static final byte DGPS_I2C_ADDR = 0x06;      /*!< Barometric sensor device address */
	public static final byte DGPS_CMD_UTC   = 0x00;      /*!< Fetch UTC */
	public static final byte DGPS_CMD_STATUS = 0x01;      /*!< Status of satellite link: 0 no link, 1 link */
	public static final byte DGPS_CMD_LAT  =  0x02;      /*!< Fetch Latitude */
	public static final byte DGPS_CMD_LONG =  0x04;      /*!< Fetch Longitude */
	public static final byte DGPS_CMD_VELO =  0x06;      /*!< Fetch velocity in cm/s */
	public static final byte DGPS_CMD_HEAD= 0x07;      /*!< Fetch heading in degrees */
	public static final byte DGPS_CMD_DIST=0x08;      /*!< Fetch distance to destination */
	public static final byte DGPS_CMD_ANGD=0x09;      /*!< Fetch angle to destination */
	public static final byte DGPS_CMD_ANGR=0x09;      /*!< Fetch angle travelled since last request */
	public static final byte DGPS_CMD_SLAT=0x0A;      /*!< Set latitude of destination */
	public static final byte DGPS_CMD_SLONG=0x0B;      /*!< Set longitude of destination */


	private SensorPort I2Cport;
	private byte address;
		
	 /**
	 * Constructor
	 * @param link the DGPS port number
	 */
    public dGPSSensor(SensorPort sensorPort) { 
        super(sensorPort); 
        I2Cport = sensorPort;
		address = DGPS_I2C_ADDR;        
        I2Cport.setType(TYPE_LOWSPEED);
		I2Cport.i2cEnable(I2CPort.STANDARD_MODE);
    } 

	int sendCommand(byte c, byte reply[], int replyLen) {

		byte args[] = {0, 0, 0, 0, 0, 0, 0};		
  		
  		args[0] = c;  		
  		int r = I2Cport.i2cStart(address, args, 0, 1, replyLen);
   		if(r != 0) return r;
  		
  		//I2Cport.i2cWaitIOComplete();
  		Delay.msDelay(50);
  		r = I2Cport.i2cComplete(reply, 0, replyLen); 				 	
  		return r;
	}

	/**
	 * Return status of link to the GPS satellites
	 * LED on dGPS should light if sattelite lock acquired
	 * @return true if GPS link is up, else false
	 */
	public boolean linkStatus() {
		byte reply[] = {0};
						
		sendCommand(DGPS_CMD_STATUS, reply, 1);		
   		return (reply[0] == 1);
  	}
  	
	/**
	 * Get the current time stored on the dGPS
	 * @return current UTC time stored on the device
	 */  	
  	public int getUTC() {
		
		byte reply[] = {0, 0, 0, 0};
		
		int r = sendCommand(DGPS_CMD_UTC, reply, 4);
		if(r < 0) return r;
		
		int time = (reply[3] & 0xFF) + 
					((reply[2] & 0xFF) << 8) + 
					((reply[1] & 0xFF) << 16) + 
					((reply[0] & 0xFF) << 24);
  		return time;
  	}

  	/**
	 * Read the current lattitude in degrees (postive=North, negative=South)
	 * @return current lattitude in decimal degrees
	 */
  	public int getLat(){
		byte reply[] = {0, 0, 0, 0};
		
 		sendCommand(DGPS_CMD_LAT, reply, 4);
		int lat = (reply[3] & 0xFF) + 
					((reply[2] & 0xFF) << 8) + 
					((reply[1] & 0xFF) << 16) + 
					((reply[0] & 0xFF) << 24);
  		return lat;	
  	}
    
	/**
	 * Read the current longitude in degrees (postive=East, negative=West)
	 * @return current longitude in decimal degrees
	 */  
    public int getLong() {
		byte reply[] = {0, 0, 0, 0};		

 		sendCommand(DGPS_CMD_LONG, reply, 4);
		int lont = (reply[3] & 0xFF) + 
					((reply[2] & 0xFF) << 8) + 
					((reply[1] & 0xFF) << 16) + 
					((reply[0] & 0xFF) << 24);
  		return lont;
    }

  	/**
	 * Read the current velocity in cm/s
	 * @return current velocity in cm/s
	 */
    public int getVelocity() {
		byte reply[] = {0, 0, 0};		
  		sendCommand(DGPS_CMD_VELO, reply, 3);
 
		int v = (reply[2] & 0xFF) + 
					((reply[1] & 0xFF) << 8) + 
					((reply[0] & 0xFF) << 16);
  		return v;
    }
    
    /**
	 * Read the current heading in degrees
	 * @return current heading in degrees
	 */
    public int getHeading() {
		byte reply[] = {0, 0};		

 		sendCommand(DGPS_CMD_HEAD, reply, 2);
 
		int h = (reply[1] & 0xFF) + 
					((reply[0] & 0xFF) << 8);
  		return h;
    } 
    
    /**
     * Read the current relative heading in degrees
     * Angle travelled since last request. See dGPS manual.
     * @return relative head
     */
     public int getRelativeHeading() {
		byte reply[] = {0, 0};		

 		sendCommand(DGPS_CMD_ANGR, reply, 2);
 
		int h = (reply[1] & 0xFF) + 
					((reply[0] & 0xFF) << 8);
  		return h;   	
     }

	/**
	 * Distance to destination in meters
	 * @return distance to destination in meters
	 */
     public int getDistanceToDest() {
		byte reply[] = {0, 0, 0, 0};		

 		sendCommand(DGPS_CMD_DIST, reply, 4);
 
		int dist = (reply[3] & 0xFF) + 
					((reply[2] & 0xFF) << 8) + 
					((reply[1] & 0xFF) << 16) + 
					((reply[0] & 0xFF) << 24);
  		return dist; 	
     }

	/**
	 * Angle to destination in degrees
	 * @return angle to destination in degrees
	 */
     public int getAngleToDest() {
		byte reply[] = {0, 0};		

 		sendCommand(DGPS_CMD_ANGD, reply, 2);
 
		int h = (reply[1] & 0xFF) + 
					((reply[0] & 0xFF) << 8);
  		return h;   	
     }

	/**
	 * Set destination latitude coordinates
	 * @param latitude destination's latitude in decimal degrees
	 * @return 0 if no error else error code
	 */
     public int setLatitude(int latitude) {
		byte reply[] = {0, 0};		
		byte args[] = {0, 0, 0, 0, 0, 0, 0};		
  		
  		// We set the latitude in the dGPS
  		args[0] = DGPS_CMD_SLAT;
		args[1] = (byte)((latitude >> 24) & 0xFF);
		args[2] = (byte)((latitude >> 16) & 0xFF);
		args[3] = (byte)((latitude >>  8) & 0xFF);
		args[4] = (byte)((latitude >>  0) & 0xFF);
  		
  		int r = I2Cport.i2cStart(address, args, 0, 5, 0);
   		if(r != 0) return r;
  		
  		//I2Cport.i2cWaitIOComplete();
  		Delay.msDelay(50);
  		r = I2Cport.i2cComplete(reply, 0, 0); 				 	
  		return r;
     }

	/**
	 * Set destination longitude coordinates
	 * @param longitude destination's longitude in decimal degrees
	 * @return 0 if no error else error code
	 */
     public int setLongitude(int longitude) {
		byte reply[] = {0, 0};		
		byte args[] = {0, 0, 0, 0, 0, 0, 0};		
  		
  		// We set the longitude in the dGPS
  		args[0] = DGPS_CMD_SLONG;
  	
		args[1] = (byte)((longitude >> 24) & 0xFF);
		args[2] = (byte)((longitude >> 16) & 0xFF);
		args[3] = (byte)((longitude >>  8) & 0xFF);
		args[4] = (byte)((longitude >>  0) & 0xFF);
  	
  		int r = I2Cport.i2cStart(address, args, 0, 5, 0);
   		if(r != 0) return r;
  		
  		//I2Cport.i2cWaitIOComplete();
  		Delay.msDelay(50);
  		r = I2Cport.i2cComplete(reply, 0, 0); 				 	
  		return r;
     }

 }
