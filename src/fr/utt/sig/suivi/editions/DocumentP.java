package fr.utt.sig.suivi.editions;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import fr.utt.sig.suivi.beans.profil.Decision;
import fr.utt.sig.suivi.beans.profil.Diplome;
import fr.utt.sig.suivi.beans.profil.Etudiant;
import fr.utt.sig.suivi.beans.profil.Inscription;
import fr.utt.sig.suivi.beans.profil.Total;
import fr.utt.sig.suivi.beans.profil.Totaux;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author naneon
 * 
 */

@SuppressWarnings("all")
public class DocumentP {

	public static final int PV_JURY_SUIVI = 0;
	public static final int PV_JURY_SUIVI_CONV = 1;
	public static final int PV_JURY_DIPLOME = 2;
	public static final int CURSUS_ETUDIANT = 3;
	public static final int DOUBLE_DIPLOME = 4;
	public static final int PV_JURY_TRI_MASTER = 5;
	public static final int PV_JURY_CV_TRI_MASTER = 6;
	private Moniteur moniteur;
	private int ncompte;
	private int typeDocument;
	private List etudiants_id;
	private List pv;
	private List pvING2;
	private List pvMST;
	private String path;
	private String name;
	private String nomEtu;
	private String libpv;
	private String diplome;
	private String numero;
	private boolean compte;
	private String export_path;
	private String fnt_path;
	private String img_path;
	private String img_url;
	private OutputStream output;
	private static String default_path = "/Users/naneon/Developpements/workspace-juno/Suivi-PP/export/";
	private static String default_name = "pv.pdf";
	protected final Log logger;
	private Etudiant etudiant;
	private static String dimg_path = "/Users/naneon/Developpements/workspace-juno/Suivi-PP/images/photo/";
	private static String dfnt_path = "/Users/naneon/Developpements/workspace-juno/Suivi-PP/WebContent/WEB-INF/fonts/";
	private int pourcentage;
	private String beConnect;

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant1) {
		etudiant = etudiant1;
	}

	public DocumentP() {
		ncompte = 0;
		typeDocument = 0;
		compte = false;
		logger = LogFactory.getLog(getClass());
		pourcentage = 0;
	}

	public DocumentP(java.util.List list, int i) {
		ncompte = 0;
		typeDocument = 0;
		compte = false;
		logger = LogFactory.getLog(getClass());
		pourcentage = 0;
		setEtudiants_id(list);
		setTypeDocument(i);
		setPath((new StringBuilder()).append(export_path).append(default_name)
				.toString());
		createDocument(path);
	}

	public void create() {
		createDocument(path);
	}

	// Construction des donnees pour la generation du pdf pv du jury
	public DocumentP(List listEtudiants, List pv, List pvING2, List pvMST,
			int i, Etudiant etudiant1, Moniteur moniteur1, String dir_export,
			String dir_fnt, String dir_img, String url_photo, String chemin,
			String libpvz, String diplome, String numero, String name,
			boolean flag) {
		ncompte = 0;
		typeDocument = 0;
		compte = false;
		logger = LogFactory.getLog(getClass());
		pourcentage = 0;
		setEtudiant(etudiant1);
		setEtudiants_id(listEtudiants);
		setTypeDocument(i);
		setMoniteur(moniteur1);
		setExport_path(dir_export);
		setFnt_path(dir_fnt);
		setImg_path(dir_img);
		setImg_url(url_photo);
		setLibpv(libpvz);
		setDiplome(diplome);
		setNumero(numero);
		setNomEtu(name);
		setCompte(flag);
		setPv(pv);
		setPvING2(pvING2);
		setPvMST(pvMST);
		if (chemin != null) {
			setPath((new StringBuilder()).append(dir_export).append(chemin)
					.toString());
		} else {
			setPath((new StringBuilder()).append(dir_export)
					.append(default_name).toString());
		}
		String s6 = "";
		if (getEtudiants_id().size() > 0) {
			try {
				s6 = (new StringBuilder()).append(getPath()).append("-tmp")
						.toString();
				setOutput(new FileOutputStream(s6));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			// creation PDF
			createDocument((new StringBuilder()).append(dir_export)
					.append(default_name).toString());
			File file = new File(s6);
			File file1 = new File(getPath());
			file.renameTo(file1);
			beConnect = "L'impression du document est terminée";
		} else {
			try {
				throw new Exception("Exception : Le nombre de données à remonter est important, merci d'affiner vos options d'impression (inclure un niveau par exemple)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				beConnect = e.getLocalizedMessage();
				e.printStackTrace();
			}
		}
	}

	public List getEtudiants_id() {
		return etudiants_id;
	}

	public void setEtudiants_id(List list) {
		etudiants_id = list;
	}

	public int getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(int i) {
		typeDocument = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name = s;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String s) {
		path = s;
	}

	// Generation du fichier PDF
	private void createDocument(String chemin) {
		// Definition des dimentions du document PDF
		Document document = new Document(PageSize.A4.rotate(), 8F, 8F, 8F, 8F);
		// Document document1 = new Document(PageSize.A4.rotate(), 8F, 8F, 8F,
		// 8F);
		Font font = new Font(1, 10F, 0);
		Font font1 = new Font(1, 10F, 3);
		Font font2 = new Font(1, 12F, 1);
		Font font3 = new Font(1, 8F, 1);
		Font font4 = new Font(1, 8F, 0);
		Font font5 = new Font(4, 8F, 0);
		Font font6 = new Font(1, 8F, 0);
		Font font7 = new Font(4, 10F, 1);
		try {
			// Construction du PDF
			PdfWriter pdfwriter = PdfWriter.getInstance(document, output);
			document.open();
			PdfContentByte pdfcontentbyte = pdfwriter.getDirectContent();
			BaseFont basefont = BaseFont.createFont("Helvetica", "Cp1252",
					false);
			BaseFont basefont1 = BaseFont.createFont((new StringBuilder())
					.append(fnt_path).append("arial.ttf").toString(),
					"Identity-H", true);
			BaseFont basefont2 = BaseFont.createFont((new StringBuilder())
					.append(fnt_path).append("arialbd.ttf").toString(),
					"Identity-H", true);
			Font font8 = new Font(basefont1, 8F, 0);
			Font font9 = new Font(basefont1, 9F, 1);
			Font font10 = new Font(basefont2, 10F, 0);
			Font font11 = new Font(basefont2, 10F, 1);
			ncompte = 0;
			Iterator lesEtudiants = etudiants_id.iterator();
			// Traitement des donnees du PDF
			do {
				if (!lesEtudiants.hasNext()) {
					break;
				}
				ncompte++;
				Etudiant unEtudiant = (Etudiant) lesEtudiants.next();
				boolean flag = true;
				try {
					// Photo de l'etudiant
					Image photoEtudiant = Image.getInstance(new URL(
							(new StringBuilder()).append(img_url)
									.append(unEtudiant.getId()).append(".jpg")
									.toString()));
					if (photoEtudiant != null) {
						photoEtudiant.scaleAbsoluteHeight(75F);
						photoEtudiant.scaleAbsoluteWidth(60F);
						photoEtudiant.setAbsolutePosition(10F,
								PageSize.A4.getWidth() - 85F);
						document.add(photoEtudiant);
					}
				} catch (Exception exception1) {
				}
				// Entete du PDF
				createTitre(document, pdfwriter, font1, font2, font3);
				PdfPTable pdfptable = createResume();
				pdfptable.getDefaultCell().setColspan(2);
				pdfptable.addCell(new Phrase((new StringBuilder())
						.append(unEtudiant.getCiv_nom()).append(" N\260")
						.append(unEtudiant.getId()).toString(), font3));
				pdfptable.getDefaultCell().setColspan(2);
				Iterator iterPv = pv.iterator();
				Iterator iterPvING2 = pvING2.iterator();
				Iterator iterPvMST = pvMST.iterator();
				//Type de document envisagé
				if (typeDocument == 0 || typeDocument == 5 || typeDocument == 6) {
					Paragraph paragraph = new Paragraph();
					paragraph.setAlignment(2);
					//Code jury en fonction du diplôme
					if (numero.length() > 0 || nomEtu.length() > 0) {
						String label = "DECISION DU JURY :";
						while (iterPv.hasNext()) {

							LinkedHashMap pvMap = (LinkedHashMap) iterPv.next();
							String code = (String) pvMap.get("CODE");
							label = label + "     " + code;
						}
						paragraph.add(new Phrase(label, font));
					} else {
						if (diplome.equals("MST")) {
							String label = "DECISION DU JURY :";
							while (iterPvMST.hasNext()) {
								LinkedHashMap pvMstMap = (LinkedHashMap) iterPvMST
										.next();
								String code = (String) pvMstMap.get("CODE");
								label = label + "	 " + code;
							}
							paragraph.add(new Phrase(label, font));
						} else if ((diplome.equals("ING2") || diplome
								.equals("ING"))) {
							String label = "DECISION DU JURY :";
							while (iterPvING2.hasNext()) {
								LinkedHashMap pvIng2Map = (LinkedHashMap) iterPvING2
										.next();
								String code = (String) pvIng2Map.get("CODE");
								label = label + "	 " + code;
							}
							paragraph.add(new Phrase(label, font));
						} else {
							String label = "DECISION DU JURY :";
							while (iterPv.hasNext()) {
								LinkedHashMap pvMap = (LinkedHashMap) iterPv
										.next();
								String code = (String) pvMap.get("CODE");
								label = label + "     " + code;
							}
							paragraph.add(new Phrase(label, font));
						}
					}
					pdfptable.addCell(paragraph);
				}
				if (typeDocument == 2) {
					Paragraph paragraph1 = new Paragraph();
					paragraph1.setAlignment(2);
					paragraph1
							.add(new Phrase(
									"DECISION DU JURY :     DIPLOME                  OUI                      NON    "
											+ " ", font));
					pdfptable.addCell(paragraph1);
				}
				if (typeDocument == 3) {
					Paragraph paragraph2 = new Paragraph();
					paragraph2.setAlignment(2);
					paragraph2.add(new Phrase(" ", font));
					pdfptable.addCell(paragraph2);
				}
				pdfptable.getDefaultCell().setColspan(0);
				pdfptable.addCell(new Phrase("N\351(e) le", font3));
				pdfptable.addCell(new Phrase((new StringBuilder())
						.append(unEtudiant.getDate_nais()).append(" \340 ")
						.append(unEtudiant.getCom_nais()).toString(), font4));
				pdfptable.addCell(new Phrase("BAC", font3));
				pdfptable.addCell(new Phrase((new StringBuilder())
						.append(unEtudiant.getBac()).append(" en ")
						.append(unEtudiant.getAn_bac()).toString(), font4));
				pdfptable.addCell(new Phrase("Conseiller", font3));
				pdfptable.addCell(new Phrase(unEtudiant.getConseiller(), font4));
				pdfptable
						.addCell(new Phrase("Dernier dipl\364me obtenu", font3));
				pdfptable.addCell(new Phrase(unEtudiant.getDer_dipl_obtenu(),
						font4));
				pdfptable
						.addCell(new Phrase("Dernier \351tablissement", font3));
				pdfptable.addCell(new Phrase(unEtudiant.getEtab_der_dipl(),
						font4));
				pdfptable.addCell(new Phrase("Formation actuelle", font3));
				String actFormation = unEtudiant.getAct_formation();
				String cMineurs = unEtudiant.getCmineurs();
				if (cMineurs.length() > 0) {
					actFormation = (new StringBuilder()).append(actFormation)
							.append("      Mineur(s) ").append(cMineurs).toString();
				}
				Phrase phrase = new Phrase(actFormation, font4);
				pdfptable.addCell(phrase);
				document.add(pdfptable);
				Paragraph paragraph3 = new Paragraph();
				paragraph3.setSpacingAfter(5F);
				document.add(paragraph3);
				float f = 1000F;
				float f1 = 0.0F;
				float f3 = 1000F;
				f = ((Diplome) unEtudiant.getDiplomes().get(0))
						.getInscriptions_adm().size() * 70;
				//Liste des diplôme de l'Etudiant
				Iterator lesDiplomesEtudiant = unEtudiant.getDiplomes().iterator();
				// Generation du corps du PDF
				do {
					if (!lesDiplomesEtudiant.hasNext()) {
						break;
					}
					Diplome unDiplomeEtudiant = (Diplome) lesDiplomesEtudiant.next();
					if (unDiplomeEtudiant.getId().equals("DOC")) {
						System.out.println(unDiplomeEtudiant.getInscriptions_uv());
						Iterator diplomeInscriptionUvs = unDiplomeEtudiant.getInscriptions_uv().iterator();
						while (diplomeInscriptionUvs.hasNext()) {
							LinkedHashMap map = (LinkedHashMap) diplomeInscriptionUvs.next();
							System.out.println("Les UV :"
									+ (String) map.get("UV"));
						}
					}
					Color color = Color.WHITE;
					if (unDiplomeEtudiant.getReoriente().equals("O")) {
						color = new Color(0.85F, 0.85F, 0.85F);
					}
					PdfPTable pdfptable1 = new PdfPTable(2);
					pdfptable1.setWidthPercentage(100F);
					pdfptable1.setSpacingAfter(5F);
					pdfptable1.setSpacingBefore(5F);
					float af[] = { 43F, 57F };
					pdfptable1.setWidths(af);
					pdfptable1.getDefaultCell().setBorder(0);
					pdfptable1.getDefaultCell().setPadding(0.0F);
					pdfptable1.getDefaultCell().setHorizontalAlignment(0);
					// Diplome, Date de debut, date de fin + numero de
					// l'étudiant
					pdfptable1.addCell(new Phrase((new StringBuilder())
							.append("\u2192 ").append(unDiplomeEtudiant.getLibelle())
							.append(" de ").append(unDiplomeEtudiant.getDebut())
							.append(" \340 ").append(unDiplomeEtudiant.getFin())
							.append(" n\260").append(unEtudiant.getId())
							.toString(), font9));
					pdfptable1.getDefaultCell().setHorizontalAlignment(0);
					// Legende, libelle + pictogramme
					if (!unDiplomeEtudiant.getLegende().isEmpty()) {
						Phrase phrase1 = new Phrase("L\351gende :", font8);
						String libProfil;
						for (Iterator diplomeLegendes = unDiplomeEtudiant.getLegende()
								.iterator(); diplomeLegendes.hasNext(); phrase1
								.add(new Chunk((new StringBuilder())
										.append(" ").append(libProfil)
										.toString(), font8))) {
							HashMap hashmap = (HashMap) diplomeLegendes.next();
							String pictoLegende = (String) hashmap
									.get("LEGENDE");
							libProfil = (String) hashmap.get("PROFIL_LIB");
							Integer integer = null;
							if (pictoLegende == null) {
								pictoLegende = " ";
								integer = new Integer(32);
							} else {
								integer = new Integer(pictoLegende);
							}
							char ac[] = { ' ', ' ' };
							Character.toChars(integer.intValue(), ac, 1);
							String legendePicto = new String(ac);
							phrase1.add(new Chunk(legendePicto, font8));
						}

						phrase1.add(new Chunk(" 3", font7));
						phrase1.add(new Chunk(" Profil minimum valid\351",
								font8));
						pdfptable1.addCell(phrase1);
					} else {
						pdfptable1.addCell(new Phrase(""));
					}
					document.add(pdfptable1);
					// Generation du tableau de calcul des UV
					int i = unDiplomeEtudiant.getCategories().size();
					int j = i + 2;
					float f5 = 8.5F;
					float af1[] = new float[j];
					for (int k = 1; k <= i; k++) {
						af1[k] = f5;
					}

					af1[0] = 8F;
					af1[j - 1] = 100F - (float) i * f5 - af1[0];
					PdfPTable pdfptable2 = new PdfPTable(j);
					pdfptable2.setWidthPercentage(100F);
					pdfptable2.getDefaultCell().setHorizontalAlignment(1);
					pdfptable2.getDefaultCell().setVerticalAlignment(5);
					pdfptable2.getDefaultCell().setBackgroundColor(
							new Color(0.9F, 0.9F, 0.9F));
					pdfptable2.getDefaultCell().setPaddingBottom(4F);
					pdfptable2.setWidths(af1);
					PdfPCell pdfpcell = new PdfPCell();
					pdfpcell.setBorder(0);
					pdfptable2.addCell(pdfpcell);
					String libCateg;
					// Generation du Header du Tableau
					for (Iterator diplomeCategories = unDiplomeEtudiant.getCategories()
							.iterator(); diplomeCategories.hasNext(); pdfptable2
							.addCell(new Phrase(libCateg, font3))) {
						LinkedHashMap linkedhashmap = (LinkedHashMap) diplomeCategories
								.next();
						libCateg = (String) linkedhashmap.get("LIB_ABR");
					}

					pdfptable2.addCell(new Phrase("Observations", font3));
					// Generation du corps du tableau(UV, observation, stage
					// etc...)
					Iterator diplomeInscriptionAdms = unDiplomeEtudiant.getInscriptions_adm()
							.iterator();
					while (diplomeInscriptionAdms.hasNext()) {
						Inscription inscription = (Inscription) diplomeInscriptionAdms
								.next();
						// System.out.println("PERIODE :" +
						// inscription.getAn_univ() +
						// inscription.getPrd_univ());
						String ss_A = inscription.getAn_univ() + "1A";
						String ss_P = inscription.getAn_univ() + "2P";
						String s8 = (new StringBuilder())
								.append(inscription.getAn_univ())
								.append(inscription.getPrd_univ()).toString();
						pdfptable2.getDefaultCell().setBackgroundColor(
								new Color(0.9F, 0.9F, 0.9F));
						pdfptable2.getDefaultCell().setHorizontalAlignment(0);
						pdfptable2.getDefaultCell().setVerticalAlignment(4);
						pdfptable2.addCell(new Phrase((new StringBuilder())
								.append(inscription.getSession()).append(" ")
								.append(inscription.getFormation()).toString(),
								font3));
						pdfptable2.getDefaultCell().setBackgroundColor(color);
						pdfptable2.getDefaultCell().setHorizontalAlignment(1);
						pdfptable2.getDefaultCell().setVerticalAlignment(4);
						pdfptable2.getDefaultCell().setPadding(2.0F);
						Object obj = null;
						String s10 = s8;
						Object obj2 = null;
						// Division du corps du tableau par catégorie
						Iterator diplomesCateories = unDiplomeEtudiant.getCategories().iterator();
						while (diplomesCateories.hasNext()) {
							LinkedHashMap linkedhashmap3 = (LinkedHashMap) diplomesCateories
									.next();
							String libAbr = (String) linkedhashmap3
									.get("LIB_ABR");
							String s11 = libAbr;
							boolean flag2 = false;
							boolean flag5 = false;
							PdfPTable caseUv = createTableUV();
							// Alimentation de chaque case du corps du tableau
							Iterator diplomesInscriptionsUvs = unDiplomeEtudiant.getInscriptions_uv()
									.iterator();
							while (diplomesInscriptionsUvs.hasNext()) {
								// if (!iterator9.hasNext()) {
								// break;
								// }
								LinkedHashMap linkedhashmap4 = (LinkedHashMap) diplomesInscriptionsUvs
										.next();
								if (linkedhashmap4 != null) {
									boolean flag6 = true;
									String periode = (String) linkedhashmap4
											.get("PERIODE");
									String diplomes = (String) linkedhashmap4
											.get("DIPLOME");

									// System.out.println("PERIODE_A :" + s8+
									// " ===>PERIODE_B :" + periode);
									String categAbr = (String) linkedhashmap4
											.get("CATEG_ABR");

									// System.out.println("LIB_ABR :" + libAbr +
									// " ===>CATEG_ABR :" + categAbr);
									if ((periode.equals(s8)
											&& categAbr.equals(libAbr) && !diplomes
												.equals("DOC"))
											|| (periode.equals(s8)
													|| periode.equals(ss_A) || periode
														.equals(ss_P))
											&& categAbr.equals(libAbr)
											&& diplomes.equals("DOC")) {
										flag2 = true;
										String uv = (String) linkedhashmap4
												.get("UV");
										// System.out.println("UV :" + uv);
										if (uv == null) {
											uv = "";
										}
										String resUv = (String) linkedhashmap4
												.get("RESUV");
										if (resUv != null) {
											resUv = " " + resUv;
										} else {
											resUv = "";
										}
										String credit = (String) linkedhashmap4
												.get("CREDIT");
										if (credit == null) {
											credit = "";
										} else {
											credit = "  " + credit;
										}
										String pictogramme = (String) linkedhashmap4
												.get("LEGENDE");
										Integer integer3 = null;
										if (pictogramme == null) {
											pictogramme = " ";
											integer3 = new Integer(32);
										} else {
											integer3 = new Integer(pictogramme);
										}
										char ac1[] = { ' ', ' ' };
										Character.toChars(integer3.intValue(),
												ac1, 1);
										String picto = new String(ac1);
										caseUv.getDefaultCell()
												.setHorizontalAlignment(0);
										caseUv.addCell(new Phrase(uv + "	",
												font6));
										caseUv.addCell(new Phrase(resUv, font6));
										caseUv.getDefaultCell()
												.setHorizontalAlignment(1);
										caseUv.addCell(new Phrase(credit, font6));
										caseUv.getDefaultCell()
												.setHorizontalAlignment(1);
										caseUv.addCell(new Phrase(picto, font8));
									}
								} else {
									pdfptable2.addCell(new Phrase("", font4));
								}
							}
							if (flag2) {
								pdfptable2.addCell(caseUv);
							} else {
								pdfptable2.addCell(new Phrase("", font4));
								Object obj1 = null;
								boolean flag3 = false;
							}
						}

						pdfptable2.getDefaultCell().setHorizontalAlignment(0);
						pdfptable2.getDefaultCell().setVerticalAlignment(4);
						pdfptable2.getDefaultCell().setPadding(2.0F);
						PdfPCell pdfpcell1 = new PdfPCell();
						pdfpcell1.setBackgroundColor(color);
						pdfpcell1.setIndent(0.0F);
						pdfpcell1.setVerticalAlignment(4);
						pdfpcell1.setVerticalAlignment(4);
						pdfpcell1.setExtraParagraphSpace(0.0F);
						pdfpcell1.setSpaceCharRatio(0.0F);
						Paragraph paragraph4 = new Paragraph(
								inscription.getDecision(), font4);
						paragraph4.setAlignment(0);
						paragraph4.setExtraParagraphSpace(0.0F);
						paragraph4.setIndentationLeft(0.0F);
						paragraph4.setLeading(font4.getSize() - 1.0F);
						pdfpcell1.addElement(paragraph4);
						String stage = inscription.getStage();
						if (stage != null) {
							stage = stage.replace("<br/>", " ");
						}
						Paragraph paragraph5 = new Paragraph(stage, font4);
						paragraph5.setLeading(font4.getSize());
						pdfpcell1.addElement(paragraph5);
						String observation = inscription.getObservation();
						if (observation != null) {
							observation = observation.replace("<br/>", " ");
						}
						Paragraph paragraph6 = new Paragraph(observation, font4);
						paragraph6.setLeading(font4.getSize());
						pdfpcell1.addElement(paragraph6);
						Paragraph paragraph7 = new Paragraph(
								inscription.getSituation(), font4);
						paragraph7.setAlignment(0);
						pdfpcell1.addElement(paragraph7);
						String soutenance = inscription.getSoutenance();
						if (soutenance != null) {
							Paragraph paragraph8 = new Paragraph(
									(new StringBuilder())
											.append("Soutenance le : ")
											.append(soutenance).toString(),
									font4);
							paragraph8.setAlignment(0);
							pdfpcell1.addElement(paragraph8);
						}
						String num_diplome = inscription.getDiplome_num();
						if (num_diplome != null) {
							Paragraph paragraph9 = new Paragraph(
									(new StringBuilder())
											.append("Dipl\364m\351 : ")
											.append(num_diplome).toString(),
									font4);
							paragraph9.setAlignment(2);
							pdfpcell1.addElement(paragraph9);
						}
						pdfptable2.addCell(pdfpcell1);
						pdfptable2.getDefaultCell().setBackgroundColor(
								new Color(0.9F, 0.9F, 0.9F));
						pdfptable2.getDefaultCell().setPadding(2.0F);
						// Corps du tableau - total par semestre ou annee
						String s19 = "Total semestre";
						if (unDiplomeEtudiant.getId().equals("DOC")) {
							s19 = "Total ann\351e";
						}
						pdfptable2.addCell(new Phrase(s19, font4));
						pdfptable2.getDefaultCell().setBackgroundColor(
								new Color(0.96F, 0.96F, 0.96F));
						pdfptable2.getDefaultCell().setHorizontalAlignment(2);
						pdfptable2.getDefaultCell().setVerticalAlignment(4);
						pdfptable2.getDefaultCell().setPaddingRight(14.5F);
						// Calcul des totaux
						for (Iterator iterator10 = unDiplomeEtudiant.getCategories()
								.iterator(); iterator10.hasNext();) {
							LinkedHashMap linkedhashmap5 = (LinkedHashMap) iterator10
									.next();
							Integer integer2 = (Integer) unDiplomeEtudiant
									.getStotal()
									.get((new StringBuilder())
											.append(s8)
											.append(linkedhashmap5
													.get("LIB_ABR")).toString());
							if (integer2 != null) {
								String s28 = integer2.toString();
								pdfptable2.addCell(new Phrase(s28, font3));
							} else {
								pdfptable2.addCell(new Phrase("", font3));
							}
						}

						if (unDiplomeEtudiant.getStotal().get(
								(new StringBuilder()).append(s8).append("TS")
										.toString()) != null) {
							pdfptable2.getDefaultCell().setHorizontalAlignment(
									0);
							pdfptable2.getDefaultCell().setPaddingRight(0.0F);
							String s23 = ((Integer) unDiplomeEtudiant.getStotal().get(
									(new StringBuilder()).append(s8)
											.append("TS").toString()))
									.toString();
							if (s23 != null) {
								pdfptable2.addCell(new Phrase(s23, font4));
							} else {
								pdfptable2.addCell(new Phrase("", font3));
							}
						} else {
							pdfptable2.getDefaultCell().setHorizontalAlignment(
									0);
							pdfptable2.getDefaultCell().setPaddingRight(0.0F);
							pdfptable2.addCell(new Phrase("", font3));
						}
					}

					if (unDiplomeEtudiant.getTotalg() != null
							&& !unDiplomeEtudiant.getTotalg().isEmpty()) {
						pdfptable2.getDefaultCell().setBackgroundColor(
								new Color(0.9F, 0.9F, 0.9F));
						pdfptable2.getDefaultCell().setPadding(2.0F);
						pdfptable2.addCell(new Phrase("Total", font4));
						pdfptable2.getDefaultCell().setBackgroundColor(
								new Color(0.96F, 0.96F, 0.96F));
						pdfptable2.getDefaultCell().setHorizontalAlignment(2);
						pdfptable2.getDefaultCell().setVerticalAlignment(4);
						pdfptable2.getDefaultCell().setPaddingRight(14.5F);
						for (Iterator iterator5 = unDiplomeEtudiant.getCategories()
								.iterator(); iterator5.hasNext();) {
							LinkedHashMap linkedhashmap1 = (LinkedHashMap) iterator5
									.next();
							Integer integer1 = (Integer) unDiplomeEtudiant
									.getTotalg()
									.get((String) linkedhashmap1.get("LIB_ABR"));
							if (integer1 != null) {
								String s9 = integer1.toString();
								pdfptable2.addCell(new Phrase(s9, font3));
							} else {
								pdfptable2.addCell(new Phrase("", font3));
							}
						}

						if (unDiplomeEtudiant.getTotalg().get("TG") != null) {
							pdfptable2.getDefaultCell().setHorizontalAlignment(
									0);
							pdfptable2.getDefaultCell().setPaddingRight(0.0F);
							String s5 = ((Integer) unDiplomeEtudiant.getTotalg()
									.get("TG")).toString();
							if (s5 != null) {
								pdfptable2.addCell(new Phrase(s5, font4));
							} else {
								pdfptable2.addCell(new Phrase("", font3));
							}
						} else {
							pdfptable2.getDefaultCell().setHorizontalAlignment(
									0);
							pdfptable2.getDefaultCell().setPaddingRight(0.0F);
							pdfptable2.addCell(new Phrase("", font3));
						}
					}
					document.add(pdfptable2);
					float f2 = pdfwriter.getVerticalPosition(false);
					boolean flag1 = false;
					if (f2 < f) {
						if (lesDiplomesEtudiant.hasNext() || !flag && f2 < 100F
								&& unDiplomeEtudiant.getTotaux().size() != 0) {
							document.newPage();
							flag1 = true;
						}
						if (flag) {
							flag = false;
						}
					}
					f = pdfptable2.getTotalHeight();
					if (unDiplomeEtudiant.getTotaux().size() != 0) {
						if (!flag1 && f2 < 100F) {
							document.newPage();
						}
						PdfPTable pdfptable3 = new PdfPTable(1);
						pdfptable3.setKeepTogether(true);
						pdfptable3.setWidthPercentage(100F);
						pdfptable3.getDefaultCell().setBorder(0);
						// Titre du tableau des totaux
						Phrase phrase2 = new Phrase((new StringBuilder())
								.append("TOTAUX ").append(unDiplomeEtudiant.getLibelle())
								.append(" de ").append(unEtudiant.getCiv_nom())
								.toString(), font10);
						phrase2.setLeading(10F);
						pdfptable3.addCell(phrase2);
						PdfPTable pdfptable5 = new PdfPTable(j);
						pdfptable5.setKeepTogether(true);
						pdfptable5.setWidthPercentage(100F);
						pdfptable5.getDefaultCell().setHorizontalAlignment(1);
						pdfptable5.getDefaultCell().setVerticalAlignment(5);
						pdfptable5.getDefaultCell().setBackgroundColor(
								new Color(0.9F, 0.9F, 0.9F));
						pdfptable5.getDefaultCell().setPaddingBottom(4F);
						pdfptable5.getDefaultCell().setPadding(2.0F);
						pdfptable5.setWidths(af1);
						pdfptable5.setHeaderRows(1);
						pdfptable5.addCell(pdfpcell);
						LinkedHashMap linkedhashmap2;
						// Generation du tableau des totaux
						for (Iterator diplomCategories = unDiplomeEtudiant.getCategories()
								.iterator(); diplomCategories.hasNext(); pdfptable5
								.addCell(new Phrase((String) linkedhashmap2
										.get("LIB_ABR"), font3))) {
							linkedhashmap2 = (LinkedHashMap) diplomCategories.next();
						}

						pdfptable5.addCell(pdfpcell);
						Iterator diplomeTotaux = unDiplomeEtudiant.getTotaux().iterator();
						do {
							if (!diplomeTotaux.hasNext()) {
								break;
							}
							Totaux totaux = (Totaux) diplomeTotaux.next();
							pdfptable5.getDefaultCell().setPadding(2.0F);
							pdfptable5.getDefaultCell().setBackgroundColor(
									new Color(0.9F, 0.9F, 0.9F));
							pdfptable5.getDefaultCell().setHorizontalAlignment(
									0);
							pdfptable5.getDefaultCell().setVerticalAlignment(4);
							pdfptable5.addCell(new Phrase(totaux.getLibelle(),
									font3));
							pdfptable5.getDefaultCell().setBackgroundColor(
									Color.WHITE);
							pdfptable5.getDefaultCell().setHorizontalAlignment(
									1);
							pdfptable5.getDefaultCell().setVerticalAlignment(5);
							int l = j;
							int i1 = totaux.getTotaux().size();
							int j1 = 0;
							for (int k1 = 0; k1 < i1; k1++) {
								Total total = (Total) totaux.getTotaux()
										.get(k1);
								pdfptable5.getDefaultCell().setColspan(
										total.getLongueur());
								if (totaux.getId() == total.getTotal_id()
										&& total.getOrdre_lg() == 1
										&& total.getOrdre_col() != 999) {
									String s14 = total.isAcquis() ? " 3" : "";
									String s16 = total.getMin() == null ? ""
											: (new StringBuilder()).append("/")
													.append(total.getMin())
													.toString();
									String s20 = total.getTotal() == null ? ""
											: total.getTotal();
									Phrase phrase3 = new Phrase(
											(new StringBuilder()).append(s20)
													.append(s16).toString(),
											font6);
									Chunk chunk = new Chunk(s14, font7);
									phrase3.add(chunk);
									pdfptable5.addCell(phrase3);
									j1 += total.getLongueur();
								}
							}

							pdfptable5.getDefaultCell().setColspan(1);
							for (int l1 = 0; l1 < l - j1 - 1; l1++) {
								pdfptable5.addCell(pdfpcell);
							}

							j1 = 0;
							boolean flag4 = false;
							for (int i2 = 0; i2 < i1; i2++) {
								Total total1 = (Total) totaux.getTotaux().get(
										i2);
								if (totaux.getId() != total1.getTotal_id()
										|| total1.getOrdre_lg() != 2) {
									continue;
								}
								flag4 = true;
								pdfptable5.getDefaultCell().setColspan(1);
								for (int k2 = 0; k2 < total1.getOrdre_col(); k2++) {
									pdfptable5.addCell(pdfpcell);
								}

								pdfptable5.getDefaultCell().setColspan(
										total1.getLongueur());
								String s17 = total1.isAcquis() ? " 3" : "";
								String s21 = total1.getMin() == null ? ""
										: (new StringBuilder()).append("/")
												.append(total1.getMin())
												.toString();
								String s24 = total1.getTotal() == null ? ""
										: total1.getTotal();
								Phrase phrase4 = new Phrase(
										(new StringBuilder()).append(s24)
												.append(s21).toString(), font6);
								Chunk chunk1 = new Chunk(s17, font7);
								phrase4.add(chunk1);
								pdfptable5.addCell(phrase4);
								j1 += total1.getLongueur()
										+ total1.getOrdre_col();
							}

							if (flag4) {
								pdfptable5.getDefaultCell().setColspan(1);
								int j2 = 0;
								while (j2 < l - j1) {
									pdfptable5.addCell(pdfpcell);
									j2++;
								}
							}
						} while (true);
						pdfptable3.addCell(pdfptable5);
						document.add(pdfptable3);
						float f4 = pdfptable5.getTotalHeight();
					}
				} while (true);
				pourcentage++;
				if (moniteur != null) {
					moniteur.setPourcentage(pourcentage);
				}
				if (lesEtudiants.hasNext()) {
					document.newPage();
				}
			} while (true);
			if (typeDocument == 0 || typeDocument == 1 || typeDocument == 2) {
				document.add(createFooter(document, pdfwriter, font1, font2,
						font3));
			}
			document.close();
			pourcentage = 100;
			if (moniteur != null) {
				moniteur.setPourcentage(pourcentage);
				moniteur.setFchemin(getPath());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	// Case pour l'affichage du calcul des UV
	private PdfPTable createTableUV() {
		PdfPTable pdfptable = null;
		try {
			pdfptable = new PdfPTable(4);
			pdfptable.setWidthPercentage(100F);
			pdfptable.getDefaultCell().setBorder(0);
			pdfptable.getDefaultCell().setPadding(0.0F);
			pdfptable.getDefaultCell().setNoWrap(true);
			pdfptable.getDefaultCell().setHorizontalAlignment(0);
			pdfptable.getDefaultCell().setVerticalAlignment(4);
			float af[] = { 60F, 25F, 20F, 15F };
			pdfptable.setWidths(af);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pdfptable;
	}

	// Titre du document PDF
	private void createTitre(Document document, PdfWriter pdfwriter, Font font,
			Font font1, Font font2) {
		try {
			if (typeDocument == 0 || typeDocument == 1 || typeDocument == 4
					|| typeDocument == 5 || typeDocument == 6) {
				document.add(createHeader(document, pdfwriter,
						(new StringBuilder()).append("PV de jury de suivi ")
								.append(libpv).toString(), font, font1, font2));
			}
			if (typeDocument == 1) {
				document.add(createHeader(
						document,
						pdfwriter,
						(new StringBuilder())
								.append("PV de jury de suivi (convoqu\351s) ")
								.append(libpv).toString(), font, font1, font2));
			}
			if (typeDocument == 2) {
				document.add(createHeader(
						document,
						pdfwriter,
						(new StringBuilder())
								.append("PV de jury de dipl\364me ")
								.append(libpv).toString(), font, font1, font2));
			}
			if (typeDocument == 3) {
				document.add(createHeader(document, pdfwriter,
						"Parcours et profil de formation", font, font1, font2));
			}
		} catch (Exception exception) {
		}
	}

	// Bas de page du document PDF
	private PdfPTable createFooter(Document document, PdfWriter pdfwriter,
			Font font, Font font1, Font font2) {
		PdfPTable pdfptable = null;
		try {
			pdfptable = new PdfPTable(2);
			pdfptable.setSpacingAfter(10F);
			pdfptable.setHorizontalAlignment(1);
			pdfptable.getDefaultCell().setHorizontalAlignment(1);
			pdfptable.getDefaultCell().setVerticalAlignment(5);
			pdfptable.getDefaultCell().setPaddingBottom(4F);
			pdfptable.getDefaultCell().setMinimumHeight(14F);
			pdfptable.setWidthPercentage(100F);
			float af[] = { 10F, 90F };
			pdfptable.setWidths(af);
			Phrase phrase = new Phrase("DATE DU JURY", font);
			pdfptable.addCell(phrase);
			phrase = new Phrase(" SIGNATURES DES MEMBRES DU JURY ", font1);
			pdfptable.addCell(phrase);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pdfptable;
	}

	// Entete du document PDF
	private PdfPTable createHeader(Document document, PdfWriter pdfwriter,
			String s, Font font, Font font1, Font font2) {
		PdfPTable pdfptable = null;
		try {
			pdfptable = new PdfPTable(3);
			pdfptable.setSpacingAfter(10F);
			pdfptable.setHorizontalAlignment(2);
			pdfptable.getDefaultCell().setHorizontalAlignment(1);
			pdfptable.getDefaultCell().setVerticalAlignment(5);
			pdfptable.getDefaultCell().setPaddingBottom(4F);
			pdfptable.getDefaultCell().setMinimumHeight(14F);
			pdfptable.setWidthPercentage(90F);
			float af[] = { 10F, 80F, 10F };
			pdfptable.setWidths(af);
			SimpleDateFormat simpledateformat = new SimpleDateFormat(
					"dd/MM/yyyy");
			Phrase phrase = new Phrase(simpledateformat.format(new Date()),
					font);
			pdfptable.addCell(phrase);
			phrase = new Phrase(s, font1);
			pdfptable.addCell(phrase);
			if (isCompte()) {
				phrase = new Phrase((new Integer(ncompte)).toString(), font2);
			} else {
				phrase = new Phrase(
						(new Integer(pdfwriter.getPageNumber())).toString(),
						font2);
			}
			pdfptable.addCell(phrase);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pdfptable;
	}

	// Tableau du resume du parcours de l'etudiant
	private PdfPTable createResume() {
		PdfPTable pdfptable = null;
		try {
			pdfptable = new PdfPTable(4);
			pdfptable.setWidthPercentage(90F);
			pdfptable.setHorizontalAlignment(2);
			pdfptable.getDefaultCell().setBorderColor(
					new Color(0.8F, 0.8F, 0.8F));
			pdfptable.getDefaultCell().setPadding(2.0F);
			pdfptable.getDefaultCell().setHorizontalAlignment(0);
			pdfptable.getDefaultCell().setVerticalAlignment(4);
			float af[] = { 15F, 35F, 15F, 35F };
			pdfptable.setWidths(af);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pdfptable;
	}

	// Test de creation de document PDF
	private void createWM(Document document, PdfWriter pdfwriter,
			BaseFont basefont) {
		PdfContentByte pdfcontentbyte = pdfwriter.getDirectContent();
		pdfcontentbyte.beginText();
		pdfcontentbyte.setColorStroke(new Color(0.95F, 0.95F, 0.95F));
		pdfcontentbyte.setColorFill(new Color(0.8F, 0.8F, 0.8F));
		pdfcontentbyte.setFontAndSize(basefont, 48F);
		pdfcontentbyte.showTextAligned(
				1,
				(new StringBuilder()).append("SPECIMEN ")
						.append(pdfwriter.getPageNumber()).toString(), document
						.getPageSize().getWidth() / 2.0F, document
						.getPageSize().getHeight() / 2.0F, 45F);
		pdfcontentbyte.endText();
	}

	public Moniteur getMoniteur() {
		return moniteur;
	}

	public void setMoniteur(Moniteur moniteur1) {
		moniteur = moniteur1;
	}

	public String getExport_path() {
		return export_path;
	}

	public void setExport_path(String s) {
		export_path = s;
	}

	public String getFnt_path() {
		return fnt_path;
	}

	public void setFnt_path(String s) {
		fnt_path = s;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String s) {
		img_path = s;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String s) {
		img_url = s;
	}

	public void setOutput(OutputStream outputstream) {
		output = outputstream;
	}

	public String getLibpv() {
		return libpv;
	}

	public void setLibpv(String s) {
		libpv = s;
	}

	/**
	 * @return the diplome
	 */
	public String getDiplome() {
		return diplome;
	}

	/**
	 * @param diplome
	 *            the diplome to set
	 */
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the nomEtu
	 */
	public String getNomEtu() {
		return nomEtu;
	}

	/**
	 * @param nomEtu
	 *            the nomEtu to set
	 */
	public void setNomEtu(String nomEtu) {
		this.nomEtu = nomEtu;
	}

	public boolean isCompte() {
		return compte;
	}

	public void setCompte(boolean flag) {
		compte = flag;
	}

	/**
	 * @return the pv
	 */
	public List getPv() {
		return pv;
	}

	/**
	 * @param pv
	 *            the pv to set
	 */
	public void setPv(List pv) {
		this.pv = pv;
	}

	/**
	 * @return the pvING2
	 */
	public List getPvING2() {
		return pvING2;
	}

	/**
	 * @param pvING2
	 *            the pvING2 to set
	 */
	public void setPvING2(List pvING2) {
		this.pvING2 = pvING2;
	}

	/**
	 * @return the pvMST
	 */
	public List getPvMST() {
		return pvMST;
	}

	/**
	 * @param pvMST
	 *            the pvMST to set
	 */
	public void setPvMST(List pvMST) {
		this.pvMST = pvMST;
	}

	/**
	 * @return the beConnect
	 */
	public String getBeConnect() {
		return beConnect;
	}

	/**
	 * @param beConnect the beConnect to set
	 */
	public void setBeConnect(String beConnect) {
		this.beConnect = beConnect;
	}

}