/**
 * 
 */
package fr.utt.sig.suivi;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import fr.utt.sig.suivi.beans.User;
import fr.utt.sig.suivi.config.Config;
import fr.utt.sig.suivi.dao.Data;
import fr.utt.sig.suivi.dao.Liste;
import fr.utt.sig.suivi.editions.DocumentP;

/**
 * @author naneon
 * 
 */
@SuppressWarnings("all")

public class SuiviController extends MultiActionController {
	private Data data;
	private Liste listes;
	private Config config;
	private static String idselected = "idselected";

	public void setSuivi(Data paramData) {
		this.data = paramData;
	}

	/**
	 * @param listes the listes to set
	 */
	public void setListes(Liste listes) {
		this.listes = listes;
	}



	public ModelAndView handleProfil(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException {
		String id = paramHttpServletRequest.getParameter("id");
		if (id != null)
			try {
				long l = Long.parseLong(id);
				paramHttpServletRequest.getSession().setAttribute(idselected,
						id);
				HashMap hashMap = new HashMap();
				hashMap.put("etudiant", this.data.getEtudiantp(l));
				return new ModelAndView("vueProfil", hashMap);
			} catch (Exception localException) {
			}
		return new ModelAndView("vueProfil");
	}

	public ModelAndView handleImpression(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		User user = (User) paramHttpServletRequest.getSession()
				.getAttribute("user");
		long l = 0;
		if (user != null) {
			if (user.isEtudiant()) {
				l = user.getId();
			} else if ((user.isAdmin()) || (user.isPersonnel())
					|| (user.isEnseignant())
					|| (user.isResponsable_uv())
					|| (user.isConseiller())) {
				String obj = (String) paramHttpServletRequest.getSession()
						.getAttribute(idselected);
				this.logger.info(" id selected = " + (String) obj);
				if (obj != null)
					try {
						l = Long.parseLong((String) obj);
					} catch (Exception localException) {
					}
			}
			Object obj = this.data.getEtudiantp(l);
			if (obj != null) {
				ArrayList arrayList = new ArrayList();
				arrayList.add(obj);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				DocumentP documentP = new DocumentP();
				documentP.setOutput(byteArrayOutputStream);
				documentP.setTypeDocument(3);
				documentP
						.setFnt_path(this.config.getProperties("dir_fnt"));
				documentP
						.setImg_path(this.config.getProperties("dir_img"));
				documentP.setImg_url(this.config
						.getProperties("url_photo"));
				documentP.setEtudiants_id(arrayList);
				documentP.create();
				paramHttpServletResponse.setContentType("application/pdf");
				paramHttpServletResponse.setHeader("Expires", "0");
				paramHttpServletResponse.setHeader("Cache-Control",
						"must-revalidate, post-check=0, pre-check=0");
				paramHttpServletResponse.setHeader("Pragma", "public");
				paramHttpServletResponse
						.setContentLength(byteArrayOutputStream.size());
				paramHttpServletResponse.getOutputStream().write(
						byteArrayOutputStream.toByteArray());
				return null;
			}
		}
		paramHttpServletResponse.sendRedirect("login.do");
		return (ModelAndView) null;
	}

	public ModelAndView handleUV(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException {
		String uv = paramHttpServletRequest.getParameter("uv");
		String per = paramHttpServletRequest.getParameter("per");
		if ((uv != null) && (per != null)) {
			String obj = per.substring(4);
			per = per.substring(0, 4);
			if (("2P".equals(obj)) || ("U".equals(obj))
					|| ("1A".equals(obj)))
				per = new Integer(new Integer(per).intValue() + 1).toString();
		}
		Object hashMap = new HashMap();
		((HashMap) hashMap).put("uv", this.data.getUV(uv, per));
		return (ModelAndView) new ModelAndView("vueUV", (Map) hashMap);
	}

	public ModelAndView handleGraphic(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException {
		HashMap hashMap = new HashMap();
		hashMap.put("vue", "/test.do");
		return new ModelAndView("vue2", hashMap);
	}

	public ModelAndView handleImage(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		paramHttpServletResponse.setContentType("image/png");
		CategoryDataset categoryDataset = createDataset();
		PieDataset pieDataset1 = createDataset3();
		PieDataset pieDataset2 = createDataset4();
		JFreeChart jFreeChart = createChart(categoryDataset);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsPNG(byteArrayOutputStream,
				jFreeChart, 600, 400, true, 10);
		paramHttpServletResponse.getOutputStream().write(
				byteArrayOutputStream.toByteArray());
		return null;
	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		defaultCategoryDataset.addValue(1.0D, "Un", "Br 1");
		defaultCategoryDataset.addValue(4.0D, "Un", "Br 2");
		defaultCategoryDataset.addValue(3.0D, "Un", "Br 3");
		defaultCategoryDataset.addValue(5.0D, "Un", "Br 4");
		defaultCategoryDataset.addValue(5.0D, "Un", "Br 5");
		defaultCategoryDataset.addValue(5.0D, "Deux", "Br 1");
		defaultCategoryDataset.addValue(7.0D, "Deux", "Br 2");
		defaultCategoryDataset.addValue(6.0D, "Deux", "Br 3");
		defaultCategoryDataset.addValue(21.415414999999999D, "Deux",
				"Br 4");
		defaultCategoryDataset.addValue(4.0D, "Deux", "Br 5");
		defaultCategoryDataset.addValue(4.0D, "Trois", "Br 1");
		defaultCategoryDataset.addValue(3.0D, "Trois", "Br 2");
		defaultCategoryDataset.addValue(2.0D, "Trois", "Br 3");
		defaultCategoryDataset.addValue(3.0D, "Trois", "Br 4");
		defaultCategoryDataset.addValue(6.0D, "Trois", "Br 5");
		defaultCategoryDataset.addValue(4.0D, "Un", "Br 6");
		defaultCategoryDataset.addValue(3.0D, "Un", "Br 7");
		defaultCategoryDataset.addValue(2.0D, "Un", "Br 8");
		defaultCategoryDataset.addValue(3.0D, "Un", "Br 9");
		defaultCategoryDataset.addValue(6.0D, "Un", "Br 10");
		defaultCategoryDataset.addValue(2.0D, "Deux", "Br 6");
		defaultCategoryDataset.addValue(9.0D, "Deux", "Br 7");
		defaultCategoryDataset.addValue(10.0D, "Deux", "Br 8");
		defaultCategoryDataset.addValue(18.0D, "Deux", "Br 9");
		defaultCategoryDataset.addValue(15.0D, "Deux", "Br 10");
		defaultCategoryDataset.addValue(0.5D, "Trois", "Br 6");
		defaultCategoryDataset.addValue(1.1254D, "Trois", "Br 7");
		defaultCategoryDataset.addValue(2.11D, "Trois", "Br 8");
		defaultCategoryDataset.addValue(3.111D, "Trois", "Br 9");
		defaultCategoryDataset.addValue(2.1155D, "Trois", "Br 10");
		return defaultCategoryDataset;
	}

	private PieDataset createDataset3() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset.setValue("One", new Double(43.200000000000003D));
		localDefaultPieDataset.setValue("Two", new Double(10.0D));
		localDefaultPieDataset.setValue("Three", new Double(27.5D));
		localDefaultPieDataset.setValue("Four", new Double(17.5D));
		localDefaultPieDataset.setValue("Five", new Double(11.0D));
		localDefaultPieDataset.setValue("Six", new Double(19.399999999999999D));
		return localDefaultPieDataset;
	}

	private PieDataset createDataset4() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset
				.setValue("Br 1", new Double(43.200000000000003D));
		localDefaultPieDataset.setValue("Br 2", new Double(10.0D));
		localDefaultPieDataset.setValue("Br 3", new Double(17.5D));
		localDefaultPieDataset.setValue("Br 4", new Double(16.25D));
		localDefaultPieDataset.setValue("Br 5", new Double(16.25D));
		return localDefaultPieDataset;
	}

	private JFreeChart createChart(CategoryDataset paramCategoryDataset) {
		int i = 150;
		JFreeChart localJFreeChart = ChartFactory.createBarChart(
				"Etudiants inscrits/branches", "Catégories", "Valeurs",
				paramCategoryDataset, PlotOrientation.VERTICAL, true, false,
				false);
		localJFreeChart.setAntiAlias(true);
		localJFreeChart.setBackgroundPaint(Color.white);
		localJFreeChart.setBorderPaint(new Color(240, 240, 240));
		localJFreeChart.setBorderVisible(false);
		localJFreeChart.setPadding(new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D));
		localJFreeChart.getLegend()
				.setBorder(new BlockBorder(Color.LIGHT_GRAY));
		localJFreeChart.getLegend()
				.setBackgroundPaint(new Color(245, 245, 245));
		CategoryPlot localCategoryPlot = localJFreeChart.getCategoryPlot();
		localCategoryPlot.setBackgroundPaint(new Color(250, 250, 250));
		NumberAxis localNumberAxis = (NumberAxis) localCategoryPlot
				.getRangeAxis();
		localNumberAxis.setStandardTickUnits(NumberAxis
				.createIntegerTickUnits());
		BarRenderer localBarRenderer = (BarRenderer) localCategoryPlot
				.getRenderer();
		localBarRenderer.setItemMargin(0.1D);
		GradientPaint localGradientPaint1 = new GradientPaint(0.0F, 0.0F,
				new Color(0, 0, 255, i), 0.0F, 0.0F, new Color(0, 0, 100, i));
		GradientPaint localGradientPaint2 = new GradientPaint(0.0F, 0.0F,
				new Color(0, 255, 0, i), 0.0F, 0.0F, new Color(0, 100, 0, i));
		GradientPaint localGradientPaint3 = new GradientPaint(0.0F, 0.0F,
				new Color(255, 0, 0, i), 0.0F, 0.0F, new Color(100, 0, 0, i));
		localBarRenderer.setSeriesPaint(0, localGradientPaint1);
		localBarRenderer.setSeriesPaint(1, localGradientPaint2);
		localBarRenderer.setSeriesPaint(2, localGradientPaint3);
		localBarRenderer.setSeriesOutlinePaint(0, new Color(0, 0, 128, i));
		localBarRenderer.setSeriesOutlinePaint(1, new Color(0, 128, 0, i));
		localBarRenderer.setSeriesOutlinePaint(2, new Color(128, 0, 0, i));
		localBarRenderer.setItemLabelsVisible(true);
		ItemLabelPosition localItemLabelPosition1 = new ItemLabelPosition(
				ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT,
				TextAnchor.CENTER_RIGHT, -1.570796326794897D);
		localBarRenderer.setPositiveItemLabelPosition(localItemLabelPosition1);
		ItemLabelPosition localItemLabelPosition2 = new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT,
				TextAnchor.CENTER_LEFT, -1.570796326794897D);
		localBarRenderer
				.setPositiveItemLabelPositionFallback(localItemLabelPosition2);
		CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		localCategoryAxis
				.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		return localJFreeChart;
	}

	private JFreeChart createChart3(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				"Etudiants inscrits/branches", paramPieDataset, true, false,
				false);
		localJFreeChart.setAntiAlias(true);
		localJFreeChart.setBackgroundPaint(new Color(245, 245, 245));
		localJFreeChart.setBorderPaint(new Color(240, 240, 240));
		localJFreeChart.setBorderVisible(true);
		localJFreeChart.setPadding(new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D));
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setBackgroundPaint(new Color(250, 250, 250));
		int i = 150;
		GradientPaint localGradientPaint1 = new GradientPaint(0.0F, 0.0F,
				new Color(0, 0, 255, i), 0.0F, 0.0F, new Color(0, 0, 100, i));
		GradientPaint localGradientPaint2 = new GradientPaint(0.0F, 0.0F,
				new Color(0, 255, 0, i), 0.0F, 0.0F, new Color(0, 100, 0, i));
		GradientPaint localGradientPaint3 = new GradientPaint(0.0F, 0.0F,
				new Color(255, 0, 0, i), 0.0F, 0.0F, new Color(100, 0, 0, i));
		return localJFreeChart;
	}

	private static JFreeChart createChart4(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				"Etudiants inscrits/branches", paramPieDataset, true, true,
				false);
		localJFreeChart.setBackgroundPaint(Color.white);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		localPiePlot3D.setBackgroundPaint(new Color(250, 250, 250));
		localPiePlot3D.setNoDataMessage("Pas de données");
		localPiePlot3D.setStartAngle(300.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setDepthFactor(0.06D);
		localPiePlot3D.setCircular(true);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setLabelBackgroundPaint(new Color(255, 241, 176));
		localPiePlot3D.setLabelOutlinePaint(new Color(255, 201, 56));
		localPiePlot3D.setLabelShadowPaint(new Color(245, 245, 245));
		localJFreeChart.getLegend()
				.setBorder(new BlockBorder(Color.LIGHT_GRAY));
		localJFreeChart.getLegend()
				.setBackgroundPaint(new Color(245, 245, 245));
		localPiePlot3D.setSectionOutlinePaint(new Color(255, 255, 255, 0));
		localPiePlot3D.setSectionPaint(0, new Color(255, 189, 99));
		localPiePlot3D.setSectionPaint(1, new Color(255, 247, 148));
		localPiePlot3D.setSectionPaint(2, new Color(222, 82, 66));
		localPiePlot3D.setSectionPaint(3, new Color(115, 140, 165));
		localPiePlot3D.setSectionPaint(4, new Color(148, 198, 156));
		return localJFreeChart;
	}

	public void setConfig(Config paramConfig) {
		this.config = paramConfig;
	}
}
