package minechem.apparatus.prefab.gui.tab;

import static codechicken.lib.gui.GuiDraw.fontRenderer;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import minechem.Config;
import minechem.apparatus.prefab.gui.container.BasicGuiContainer;
import minechem.helper.HTTPHelper;
import minechem.helper.LocalizationHelper;
import minechem.helper.StringHelper;

/**
 *
 * @author jakimfett
 */
public class PatreonGuiTab extends BasicGuiTab
{
    String link;
    String linkText;
    List<String> linkTextList;
    int linkColor;

    public static boolean enable = Config.enablePatreon;
    public static int defaultSide = 0;
    public static int defaultHeaderColor = 14797103;
    public static int defaultSubHeaderColor = 11186104;
    public static int defaultTextColor = 16777215;
    public static int defaultBackgroundColor = 5592405;

    public PatreonGuiTab(BasicGuiContainer gui)
    {
        super(gui, LocalizationHelper.getLocalString("tab.patreon.text"), 0);
        this.backgroundColor = Color.CYAN.getRGB();
        this.enabled = Config.enablePatreon;
        this.link = "http://jakimfett.com/patreon";
        this.linkText = LocalizationHelper.getLocalString("tab.patreon.linkText");
        this.tabTitle = "tab.patreon.headerText";
        this.tabTooltip = "tab.patreon.tooltip";
        this.linkColor = Color.ORANGE.getRGB();
        this.linkTextList = Arrays.asList(link);
    }

    @Override
    public void draw()
    {
        super.draw();
        if (isEnabled())
        {
            if (isFullyOpened())
            {
                getFontRenderer().drawString(linkText, getLinkX(), getLinkY(), linkColor, true);

                if (isLinkAtOffsetPosition(gui.getMouseX(), gui.getMouseY()))
                {
                    gui.drawTooltip(linkTextList);
                }
            }
        }
    }

    @Override
    public String getIcon()
    {
        return "patreon";
    }

    public boolean isLinkAtOffsetPosition(int mouseX, int mouseY)
    {
        if (mouseX >= getLinkX())
        {
            if (mouseX <= getLinkX() + fontRenderer.getStringWidth(linkText))
            {
                if (mouseY >= getLinkY())
                {
                    if (mouseY <= getLinkY() + StringHelper.getSplitStringHeight(fontRenderer, linkText, fontRenderer.getStringWidth(linkText)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean onMousePressed(int x, int y, int z)
    {
        if (isEnabled()&&isFullyOpened()&&isLinkAtOffsetPosition(x, y))
        {
            HTTPHelper.openURL(link);
            return false;
        }
        
        return super.onMousePressed(x, y, z);
    }

    public int getLinkX()
    {
        return posXOffset();
    }

    public int getLinkY()
    {
        return getPosY() + maxHeight - StringHelper.getSplitStringHeight(fontRenderer, linkText, fontRenderer.getStringWidth(linkText)) - 5;
    }
}
