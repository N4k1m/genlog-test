/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package godclass;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import gui.mediator.ColleagueColoredLabel;
import dragdrop.DragDropEvent;
import dragdrop.DragDropListener;
import dragdrop.MultipleDragDropEvent;
import gui.mediator.ColleagueNumberSpinner;
import gui.mediator.Mediator;
import gui.spinner.ValueChangedEvent;
import gui.spinner.ValueChangedListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import utils.MessageBoxes;

/**
 *
 * @author Nakim
 */
public class GodClassMediator implements Mediator,
                                         DragDropListener
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final GodClassCalculator calculator;

    private ColleagueNumberSpinner atfdLimitSpinner;
    private ColleagueColoredLabel atfdTFLabel;
    private ColleagueColoredLabel atfdValueLabel;

    private ColleagueNumberSpinner wmcLimitSpinner;
    private ColleagueColoredLabel wmcTFLabel;
    private ColleagueColoredLabel wmcValueLabel;

    private ColleagueNumberSpinner mccLimitSpinner;
    private ColleagueColoredLabel mccTFLabel;
    private ColleagueColoredLabel mccValueLabel;

    private ColleagueNumberSpinner tccLimitSpinner;
    private ColleagueColoredLabel tccTFLabel;
    private ColleagueColoredLabel tccValueLabel;

    private ColleagueColoredLabel godClassTFLabel;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public GodClassMediator()
    {
        this.calculator = new GodClassCalculator(
            GodClassCalculator.DEFAULT_WMC_LIMIT,
            GodClassCalculator.DEFAULT_MCC_LIMIT,
            GodClassCalculator.DEFAULT_TCC_LIMIT,
            GodClassCalculator.DEFAULT_ATFD_LIMIT);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ATFD register methods">
    public void registerATFDLimitSpinner(ColleagueNumberSpinner atfdSpinner)
    {
        this.atfdLimitSpinner = atfdSpinner;
        this.atfdLimitSpinner.setValue(this.calculator.getATFDLimit());
        this.atfdLimitSpinner.addValueChangedListener(new ValueChangedListener()
        {
            @Override
            public void valueChanged(ValueChangedEvent event)
            {
                atfdLimitValueChanged(event);
            }
        });
    }

    public void registerATFDValueLabel(ColleagueColoredLabel atfdValueLabel)
    {
        this.atfdValueLabel = atfdValueLabel;
    }

    public void registerATFDTFLabel(ColleagueColoredLabel atfdTFLabel)
    {
        this.atfdTFLabel = atfdTFLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="WMC register methods">
    public void registerWMCLimitSpinner(ColleagueNumberSpinner wmcSpinner)
    {
        this.wmcLimitSpinner = wmcSpinner;
        this.wmcLimitSpinner.setValue(this.calculator.getWMCLimit());
        this.wmcLimitSpinner.addValueChangedListener(new ValueChangedListener()
        {
            @Override
            public void valueChanged(ValueChangedEvent event)
            {
                wmcLimitValueChanged(event);
            }
        });
    }

    public void registerWMCValueLabel(ColleagueColoredLabel wmcValueLabel)
    {
        this.wmcValueLabel = wmcValueLabel;
    }

    public void registerWMCTFLabel(ColleagueColoredLabel wmcTFLabel)
    {
        this.wmcTFLabel = wmcTFLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MCC register methods">
    public void registerMCCLimitSpinner(ColleagueNumberSpinner mccSpinner)
    {
        this.mccLimitSpinner = mccSpinner;
        this.mccLimitSpinner.setValue(this.calculator.getMCCLimit());
        this.mccLimitSpinner.addValueChangedListener(new ValueChangedListener()
        {
            @Override
            public void valueChanged(ValueChangedEvent event)
            {
                mccLimitValueChanged(event);
            }
        });
    }

    public void registerMCCValueLabel(ColleagueColoredLabel mccValueLabel)
    {
        this.mccValueLabel = mccValueLabel;
    }

    public void registerMCCTFLabel(ColleagueColoredLabel mccTFLabel)
    {
        this.mccTFLabel = mccTFLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TCC register methods">
    public void registerTCCLimitSpinner(ColleagueNumberSpinner tccSpinner)
    {
        this.tccLimitSpinner = tccSpinner;
        this.tccLimitSpinner.setValue(this.calculator.getTCCLimit());
        this.tccLimitSpinner.addValueChangedListener(new ValueChangedListener()
        {
            @Override
            public void valueChanged(ValueChangedEvent event)
            {
                tccLimitValueChanged(event);
            }
        });
    }

    public void registerTCCValueLabel(ColleagueColoredLabel tccValueLabel)
    {
        this.tccValueLabel = tccValueLabel;
    }

    public void registerTCCTFLabel(ColleagueColoredLabel tccTFLabel)
    {
        this.tccTFLabel = tccTFLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="God Class register method">
    public void registerGodClassTFLabel(ColleagueColoredLabel godClassTFLabel)
    {
        this.godClassTFLabel = godClassTFLabel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Drag & drop event handlers">
    @Override
    public void fileDropped(DragDropEvent event)
    {
        System.out.println("File dropped : " + event.getFile().getName());

        // Check if its a java file
        if (!getFileExtension(event.getFile()).equalsIgnoreCase("java"))
        {
            MessageBoxes.ShowError("Veuillez déposer un fichier java", "Action impossible");
            return;
        }

        this.CalculateMetrics(event.getFile());
    }

    @Override
    public void filesDropped(MultipleDragDropEvent event)
    {
        MessageBoxes.ShowInfo("Vous ne pouvez déposer qu'un seul fichier à la fois",
                              "Action impossible");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Colleague Spinner event handlers">
    protected void atfdLimitValueChanged(ValueChangedEvent event)
    {
        double newATFDLimit = event.getDoubleValue();
        if (newATFDLimit >= 0)
        {
            this.calculator.setATFDLimit(newATFDLimit);
            this.updateATFDLabels();
            this.updateGodClassTFLabel();
        }
    }

    protected void wmcLimitValueChanged(ValueChangedEvent event)
    {
        double newWMCLimit = event.getDoubleValue();
        if (newWMCLimit >= 0)
        {
            this.calculator.setWMCLimit(newWMCLimit);
            this.updateWMCLabels();
            this.updateGodClassTFLabel();
        }
    }

    protected void mccLimitValueChanged(ValueChangedEvent event)
    {
        double newMCCLimit = event.getDoubleValue();
        if (newMCCLimit >= 0)
        {
            this.calculator.setMCCLimit(newMCCLimit);
            this.updateMCCLabels();
            this.updateGodClassTFLabel();
        }
    }

    protected void tccLimitValueChanged(ValueChangedEvent event)
    {
        double newTCCLimit = event.getDoubleValue();
        if (newTCCLimit >= 0)
        {
            this.calculator.setTCCLimit(newTCCLimit);
            this.updateTCCLabels();
            this.updateGodClassTFLabel();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private static String getFileExtension(File file)
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private void CalculateMetrics(final File file)
    {
        try
        {
            FileInputStream in = new FileInputStream(file);

            CompilationUnit cu;
            try
            {
                // Parse the file
                cu = JavaParser.parse(in);
            }
            finally
            {
                in.close();
            }

            // Calcule tous les metriques
            this.calculator.calculate(cu);

            // Met à jour les labels du GUI
            this.updateATFDLabels();
            this.updateWMCLabels();
            this.updateMCCLabels();
            this.updateTCCLabels();
            this.updateGodClassTFLabel();
        }
        catch (ParseException | IOException ex)
        {
            MessageBoxes.ShowError(ex.getMessage(), "Une erreur est survenue");
        }
    }

    private void updateATFDLabels()
    {
        this.atfdValueLabel.setText(String.valueOf(this.calculator.getATFD()));

        if (this.calculator.getATFD() > this.calculator.getATFDLimit())
            this.atfdTFLabel.setRedText("TRUE");
        else
            this.atfdTFLabel.setGreenText("FALSE");
    }

    private void updateWMCLabels()
    {
        this.wmcValueLabel.setText(String.valueOf(this.calculator.getWMC()));

        if (this.calculator.getWMC() >= this.calculator.getWMCLimit())
            this.wmcTFLabel.setRedText("TRUE");
        else
            this.wmcTFLabel.setGreenText("FALSE");
    }

    private void updateMCCLabels()
    {
        this.mccValueLabel.setText(String.valueOf(this.calculator.getMCC()));

        if (this.calculator.getMCC() >= this.calculator.getMCCLimit())
            this.mccTFLabel.setRedText("TRUE");
        else
            this.mccTFLabel.setGreenText("FALSE");
    }

    private void updateTCCLabels()
    {
        this.tccValueLabel.setText(String.valueOf(this.calculator.getTCC()));

        if (this.calculator.getTCC() < this.calculator.getTCCLimit())
            this.tccTFLabel.setRedText("TRUE");
        else
            this.tccTFLabel.setGreenText("FALSE");
    }

    private void updateGodClassTFLabel()
    {
        if (this.calculator.isGodClass())
            this.godClassTFLabel.setRedText("TRUE");
        else
            this.godClassTFLabel.setGreenText("FALSE");
    }
    //</editor-fold>
}
