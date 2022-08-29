package it.unibs.fp.archiviocd;

import java.util.ArrayList;
import java.util.List;

import it.unibs.fp.mylib.EstrazioniCasuali;

/**
 * <p>
 * Contiene tutti i CD posseduti da una persona e permette a quest'ultima di gestirli.
 * </p>
 * @author Davide Leone - 723335
 *
 */
public class ArchivioCd {

	public static final String INDICIZZAZIONE_CD = "CD n° %d:%n";
	public static final String MSG_ARCHIVIO_VUOTO = "L'archivio CD e' vuoto.";
	
	private List<Cd> elencoCD;
	
	public ArchivioCd() {
		this.elencoCD = new ArrayList<Cd>();
	}
	
	/**
	 * <p> Aggiunge un CD all'elenco.</p>
	 * @param cdDaAggiungere
	 */
	public void aggiungiCD(Cd cdDaAggiungere) {
		this.elencoCD.add(cdDaAggiungere);
	}
	

	/**
	 * <p>
	 * Dopo aver estratto casualmente un CD dall'elenco, estrae casualmente un brano da esso.
	 * </p>
	 * @return Brano scelto casualmente.
	 */
	public Brano selezioneCasuale() {
		if(this.elencoCD.size() == 0)
		{
			return null;
		}
		
		int estratto = EstrazioniCasuali.estraiIntero(0, this.elencoCD.size() - 1);
		
		Cd cd = this.elencoCD.get(estratto);
		
		return cd.selezioneCasualeBrano();
	}
	
	/**
	 * <p>Trova un CD tramite il titolo cercato dall'utente.</p>
	 * @param titoloCercato
	 * @return CD cercato.
	 */
	public Cd trovaCD(String titoloCercato) {
		for(Cd cd : this.elencoCD) {
			if(cd.confrontaTitoloCD(titoloCercato))
			{
				return cd;
			}
		}
		
		return null;
	}
	
	/**
	 * <p>Elimina dall'elenco dei CD un CD specifico.</p>
	 */
	public void eliminaCD(Cd cd) {
		this.elencoCD.remove(cd);
	}
	
	/**
	 * <p>Dopo aver verificato la presenza del CD cercato nell'elenco,
	 * elimina il CD scelto dall'utente.
	 * </p>
	 * @param titoloCercato
	 * @see #trovaCD(String)
	 * @see #eliminaCD(Cd) 
	 */
	public void eliminaCD(String titoloCercato) {
		Cd cd = trovaCD(titoloCercato);
		if(cd != null)
		{
			this.eliminaCD(cd);
		}
	}
	
	/**
	 * <p>
	 * Fornisce una descrizione dei CD contenuti al suo interno mostrando:
	 * <ul>
	 * <li>Messaggio di avviso qualora l'archivio sia vuoto;</li>
	 * <li>Elenco di tutti i CD al suo interno (con la loro descrizione).</li>
	 * </ul>
	 * </p>
	 * @return Rappresentazione tramite stringa dell'oggetto ArchivioCd.
	 */
	@Override
	public String toString() {
		if(this.elencoCD.size() == 0)
		{
			return MSG_ARCHIVIO_VUOTO;
		}
		
		StringBuffer descrizioneCD = new StringBuffer();
		for(int i = 0; i < this.elencoCD.size(); i++) {
			descrizioneCD.append(String.format(INDICIZZAZIONE_CD, i+1));
			Cd cd = this.elencoCD.get(i);
			descrizioneCD.append(cd.toString());
		}
		
		return descrizioneCD.toString();	
	}
	
	public List<Cd> getElencoCD() {
		return elencoCD;
	}
	
}
