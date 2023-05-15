package cchef.jtrain.self.code_comp.outputdata;


import cchef.jtrain.self.code_comp.datatypes.outputtypes.DTOutput;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
* Contains Timestamp of when a report was run, and the consolidated data.
**/
public class NonAuditable implements DataSnapshot{
private final String UTC_OFFSET;
private final ZonedDateTime CREATED;
private final DTOutput DT_OUTPUT;
// Enum of report types??

public NonAuditable(final DTOutput DT_OUTPUT, final String UTC_OFFSET) {
      this.UTC_OFFSET = "UTC+2";
      this.CREATED = Instant.now().atZone(ZoneId.of(UTC_OFFSET)); // if offset were dynamic
      this.DT_OUTPUT = DT_OUTPUT;
}

public String getUTC_OFFSET() {
      return UTC_OFFSET;
}

public ZonedDateTime getCREATED() {
      return CREATED;
}

public DTOutput getDT_OUTPUT() {
      return DT_OUTPUT;
}

}
