package org.hwbot.api.esports;

import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class ScheduleDTOTest {

    @org.junit.Test
    public void testFillGaps() throws Exception {

    }

    @org.junit.Test
    public void testAdd() throws Exception {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");

        ScheduleDTO schedule = new ScheduleDTO(2015, 1);

        schedule.add("Div I Round 1", "challenger", "challenger", new CompetitionScheduleDTO(1, "Div I Round 1", "div1_1", "DIV1", f.parse("01/02/15"), f.parse("31/03/15")));
        schedule.add("Div I Round 2", "challenger", "challenger", new CompetitionScheduleDTO(2, "Div I Round 2", "div1_2", "DIV2", f.parse("01/05/15"), f.parse("30/06/15")));
        schedule.add("Div I Round 3", "challenger", "challenger", new CompetitionScheduleDTO(3, "Div I Round 3", "div1_3", "DIV3", f.parse("01/08/15"), f.parse("30/09/15")));

        schedule.fillGaps();

        List<CompetitionScheduleRowDTO> challenger = schedule.getRowsOfType("challenger");
        System.out.println(challenger);
        org.junit.Assert.assertEquals("1 type of challenger", challenger.size(), 1);
        CompetitionScheduleRowDTO competitionScheduleRowDTO = challenger.get(0);
        org.junit.Assert.assertEquals("9 slots. 12 months, 3x2 competitions", competitionScheduleRowDTO.getList().size(), 9);
        int i = 0;
        // System.out.println(competitionScheduleRowDTO.getList());
        for (CompetitionScheduleDTO dto : competitionScheduleRowDTO.getList()) {
            System.out.println( "." + dto);
        }
        org.junit.Assert.assertEquals("nothing in januari", null, competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("round 1 in feb - mar", "Div I Round 1", competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in apr", null, competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("round 2 in may - june", "Div I Round 2", competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in juli", null, competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in aug - sep", "Div I Round 3", competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in okt", null, competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in nov", null, competitionScheduleRowDTO.getList().get(i++).getName());
        org.junit.Assert.assertEquals("nothing in dec", null, competitionScheduleRowDTO.getList().get(i++).getName());
    }
}