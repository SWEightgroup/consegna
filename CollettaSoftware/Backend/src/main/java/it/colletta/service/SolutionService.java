package it.colletta.service;

import it.colletta.library.FreelingAdapterInterface;
import it.colletta.library.FreelingAdapterSocket;
import it.colletta.model.SolutionModel;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SolutionService {

  private final String host = "localhost";
  private final int port = 65021;


  /**
   * Return an automatic correction.
   *
   * @param correctionText Phrase text
   * @return Solution
   * @throws IOException Exception
   */
  public SolutionModel getAutomaticCorrection(final String correctionText) throws IOException {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    SolutionModel solutionModel =
        SolutionModel.builder().solutionText(freelingLibrary.getCorrection(correctionText).trim())
            .reliability(0).dateSolution(System.currentTimeMillis()).build();
    freelingLibrary.closeConnection();
    return solutionModel;
  }

}
