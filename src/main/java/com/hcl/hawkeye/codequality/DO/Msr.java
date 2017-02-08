package com.hcl.hawkeye.codequality.DO;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Msr implements Serializable {
    
    private String val;

    private String frmt_val;

    private String key;

    public String getVal ()
    {
        return val;
    }

    public void setVal (String val)
    {
        this.val = val;
    }

    public String getFrmt_val ()
    {
        return frmt_val;
    }

    public void setFrmt_val (String frmt_val)
    {
        this.frmt_val = frmt_val;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "[val = "+val+", frmt_val = "+frmt_val+", key = "+key+"]";
    }

}
