package com.dodone.dodone.service;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.util.Date;

import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

public class ICSService {

    public void generateICalendarFile() throws IOException, ParseException {
        // Create a TimeZone
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone("America/New_York");

        // Start and end time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse("2023-01-01 12:00:00");
        Date endDate = sdf.parse("2023-01-01 13:00:00");

        // Create a VEvent
        VEvent event = new VEvent((Temporal) new DateTime(startDate), (Temporal) new DateTime(endDate), "Sample Event");

        // Add the event to a Calendar
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Your Company//Your Application//EN"));
//        calendar.getProperties().add(Version.VERSION_2_0);
//        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getComponents().add(event);

        // Write the calendar to a file
        try (FileOutputStream fout = new FileOutputStream("sample.ics")) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);
        }
    }
}
