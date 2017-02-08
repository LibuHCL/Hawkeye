package com.hcl.hawkeye.codequality.DO;

public class Resource {
	
	 private String id;

	    private Msr[] msr;

	    private String creationDate;

	    private String lname;

	    private String scope;

	    private String name;

	    private String qualifier;

	    private String date;

	    private String key;

	    private String version;

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public Msr[] getMsr ()
	    {
	        return msr;
	    }

	    public void setMsr (Msr[] msr)
	    {
	        this.msr = msr;
	    }

	    public String getCreationDate ()
	    {
	        return creationDate;
	    }

	    public void setCreationDate (String creationDate)
	    {
	        this.creationDate = creationDate;
	    }

	    public String getLname ()
	    {
	        return lname;
	    }

	    public void setLname (String lname)
	    {
	        this.lname = lname;
	    }

	    public String getScope ()
	    {
	        return scope;
	    }

	    public void setScope (String scope)
	    {
	        this.scope = scope;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getQualifier ()
	    {
	        return qualifier;
	    }

	    public void setQualifier (String qualifier)
	    {
	        this.qualifier = qualifier;
	    }

	    public String getDate ()
	    {
	        return date;
	    }

	    public void setDate (String date)
	    {
	        this.date = date;
	    }

	    public String getKey ()
	    {
	        return key;
	    }

	    public void setKey (String key)
	    {
	        this.key = key;
	    }

	    public String getVersion ()
	    {
	        return version;
	    }

	    public void setVersion (String version)
	    {
	        this.version = version;
	    }

	    @Override
	    public String toString()
	    {
	        return "[id = "+id+", msr = "+msr+", creationDate = "+creationDate+", lname = "+lname+", scope = "+scope+", name = "+name+", qualifier = "+qualifier+", date = "+date+", key = "+key+", version = "+version+"]";
	    }


}
