package data.model.atomicData;

import java.util.Date;

public class DateTime {
    @Override
    public String toString() {
	return "DateTime [date=" + date + "]";
    }

    Date date;

    public DateTime(final Date date) {
	this.date = date;
    }

    public DateTime() {
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

}
