package org.hwbot.api.esports;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.SubmissionDTO;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionStageDTO {

    private List<SubmissionDTO> submissions;

    /** The value for the contestBenchmarkId field */
    private int contestBenchmarkId;
    private String label;
    private long startdate;
    private long enddate;
    private int applicationId;
    private int contestId;
    private String pointsDistribution;
    private CompetitionRoundDTO round;
    
    // leader

    // derived
    private int participants;
    private String timeLeft;
    private String ends;
    
    private List<String> limitations;
    private List<SubmissionDTO> latestSubmissions;
    private List<StageRankDTO> stageRanking;
}
