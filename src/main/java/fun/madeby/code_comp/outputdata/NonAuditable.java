package fun.madeby.code_comp.outputdata;


import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;

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

public NonAuditable(DTOutput dtOutput, String UtcOffset) {
      this.UTC_OFFSET = UtcOffset;
      this.CREATED = Instant.now().atZone(ZoneId.of(UTC_OFFSET)); // if offset were dynamic
      this.DT_OUTPUT = dtOutput;
}

public String getUTC_OFFSET() {
      return UTC_OFFSET;
}

@Override
public ZonedDateTime getCREATED() {
      return CREATED;
}

@Override
public DTOutput getDT_OUTPUT() {
      return DT_OUTPUT;
}


}
