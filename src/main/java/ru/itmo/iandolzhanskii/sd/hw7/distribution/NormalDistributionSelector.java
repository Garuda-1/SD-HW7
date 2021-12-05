package ru.itmo.iandolzhanskii.sd.hw7.distribution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma1.NegOneSigma;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma2.NegTwoSigma;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma3.NegThreeSigma;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma1.PosOneSigma;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma2.PosTwoSigma;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma3.PosThreeSigma;
import org.apache.commons.math3.distribution.NormalDistribution;

@Component
public class NormalDistributionSelector {

    private final Sigma negOneSigma;
    private final Sigma negTwoSigma;
    private final Sigma negThreeSigma;
    private final Sigma posOneSigma;
    private final Sigma posTwoSigma;
    private final Sigma posThreeSigma;

    public NormalDistributionSelector(Sigma negOneSigma, Sigma negTwoSigma, Sigma negThreeSigma,
                                      Sigma posOneSigma, Sigma posTwoSigma, Sigma posThreeSigma) {
        this.negOneSigma = negOneSigma;
        this.negTwoSigma = negTwoSigma;
        this.negThreeSigma = negThreeSigma;
        this.posOneSigma = posOneSigma;
        this.posTwoSigma = posTwoSigma;
        this.posThreeSigma = posThreeSigma;
    }

    public void sample(double mean, double sd, long count) {
        NormalDistribution normalDistribution = new NormalDistribution(mean, sd);

        for (int i = 0; i < count; i++) {
            double x = normalDistribution.sample();
            double m = normalDistribution.getMean();
            double s = normalDistribution.getStandardDeviation();
            Sigma acceptor = null;
            if (-3 * s + m <= x && x < -2 * s + m) {
                acceptor = negThreeSigma;
            } else if (x < -1 * s + m) {
                acceptor = negTwoSigma;
            } else if (x < m) {
                acceptor = negOneSigma;
            } else if (x < 1 * s + m) {
                acceptor = posOneSigma;
            } else if (x < 2 * s + m) {
                acceptor = posTwoSigma;
            } else if (x < 3 * s + m) {
                acceptor = posThreeSigma;
            }
            if (acceptor != null) {
                acceptor.accept();
            }
        }
    }
}
