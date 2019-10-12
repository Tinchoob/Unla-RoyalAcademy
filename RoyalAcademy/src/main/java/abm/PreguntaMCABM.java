package abm;

import javax.transaction.Transactional;

import datos.PreguntaMC;

@Transactional
public interface PreguntaMCABM extends PreguntaABM<PreguntaMC> {
    
}