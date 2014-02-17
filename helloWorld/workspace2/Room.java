package sprint1;

public class Room {
	private String roomID;
	private String name;
	
	public Room(String roomID, String name) {
		this.roomID = roomID;
		this.name = name;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
