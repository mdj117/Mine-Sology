/*
 * Copyright 2016 MovingBlocks
 * Credit: Josharias
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.mine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import org.terasology.math.geom.BaseVector3i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2i;
import org.terasology.world.generation.Region;
import org.terasology.world.viewer.layers.AbstractFacetLayer;
import org.terasology.world.viewer.layers.Renders;

@Renders(value = HouseFacet.class, order = 4000)
public class HouseFacetLayer extends AbstractFacetLayer {

    private Color fillColor = new Color(224, 128, 128, 128);
    private Color frameColor = new Color(224, 128, 128, 224);

    @Override
    public void render(BufferedImage img, Region region) {
        HouseFacet houseFacet = region.getFacet(HouseFacet.class);

        Graphics2D g = img.createGraphics();

        int dx = region.getRegion().minX();
        int dy = region.getRegion().minZ();
        g.translate(-dx, -dy);

        for (Entry<BaseVector3i, House> entry : houseFacet.getWorldEntries().entrySet()) {
            int extent = entry.getValue().getExtent();

            BaseVector3i center = entry.getKey();
            g.setColor(fillColor);
            g.fillRect(center.x() - extent, center.z() - extent, 2 * extent, 2 * extent);
            g.setColor(frameColor);
            g.drawRect(center.x() - extent, center.z() - extent, 2 * extent, 2 * extent);
        }

        g.dispose();
    }

    @Override
    public String getWorldText(Region region, int wx, int wy) {
        HouseFacet houseFacet = region.getFacet(HouseFacet.class);

        for (Entry<BaseVector3i, House> entry : houseFacet.getWorldEntries().entrySet()) {
            int extent = entry.getValue().getExtent();

            BaseVector3i center = entry.getKey();
            Vector2i min = new Vector2i(center.x() - extent, center.z() - extent);
            Vector2i max = new Vector2i(center.x() + extent, center.z() + extent);
            if (Rect2i.createFromMinAndMax(min, max).contains(wx, wy)) {
                return "House";
            }
        }

        return null;
    }

}