/*
 * Copyright 2016 MovingBlocks
 *
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
package org.terasology.mine.lava;

import org.terasology.math.TeraMath;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.utilities.procedural.BrownianNoise;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.PerlinNoise;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Updates;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;
// The provider for the lava lakes
@RegisterPlugin
@Updates(@Facet(SurfaceHeightFacet.class))
public class LavaProvider implements FacetProviderPlugin {

    private Noise lavaNoise;

    @Override
    public void setSeed(long seed) {
        lavaNoise = new SubSampledNoise(new BrownianNoise(new PerlinNoise(seed + 2), 4), new Vector2f(0.002f, 0.002f), 1);
    }

    @Override
    public void process(GeneratingRegion region) {
        SurfaceHeightFacet facet = region.getRegionFacet(SurfaceHeightFacet.class);
        float lavaDepth = 10;
        Rect2i processRegion = facet.getWorldRegion();
        for (BaseVector2i position : processRegion.contents()) {
            float additiveLavaDepth = lavaNoise.noise(position.x(), position.y()) * lavaDepth;
            additiveLavaDepth = TeraMath.clamp(additiveLavaDepth, -lavaDepth, 0);
            facet.setWorld(position, facet.getWorld(position) + additiveLavaDepth);
        }
    }
}