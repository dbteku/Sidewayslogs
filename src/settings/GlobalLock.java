package settings;

import interfaces.Setting;

public class GlobalLock implements Setting{

	private final String NAME = "Global Lock";
	private boolean isEnabled;

	public GlobalLock(boolean enabled) {
		this.isEnabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public boolean equals(Object obj) {
		boolean areEqual = false;
		try{
		Setting setting = (Setting)obj;
		String settingName = setting.getName();
		String lowerCase = settingName.toLowerCase();
		String noSpaces = lowerCase.replaceAll(" ", "");
		String lowerCaseName = NAME.toLowerCase();
		String noSpacesName = lowerCaseName.replaceAll(" ", "");
		areEqual = (noSpaces.equalsIgnoreCase(noSpacesName) && setting.isEnabled() == this.isEnabled);
		}catch(ClassCastException e){
			
		}
		
		return areEqual;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Override
	public String toString() {
		return NAME + " " + isEnabled;
	}
	
}
