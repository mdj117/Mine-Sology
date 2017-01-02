/*
 * Copyright 2016 MovingBlocks
 * Credit : Josharias
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.mine;

import org.terasology.math.geom.Vector3i;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.Updates;
import org.terasology.world.generation.facets.DensityFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Updates(@Facet(DensityFacet.class))
@Requires(@Facet(CaveFacet.class))
public class CaveToDensityProvider implements FacetProviderPlugin {
    @Override
    public void setSeed(long seed) {
    }

    @Override
    public void process(GeneratingRegion region) {
        CaveFacet caveFacet = region.getRegionFacet(CaveFacet.class);
        DensityFacet densityFacet = region.getRegionFacet(DensityFacet.class);

        for (Vector3i pos : region.getRegion()) {
            if (caveFacet.getWorld(pos)) {
                densityFacet.setWorld(pos, -1f);
            }
        }
    }
}