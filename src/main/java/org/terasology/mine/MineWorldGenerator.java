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
package org.terasology.mine;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "Mine", displayName = "Mine-sology")
public class MineWorldGenerator extends BaseFacetedWorldGenerator {

    public MineWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(0)
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(1))
                .addProvider(new MountainsProvider())
                .addProvider(new CaveFacetProvider())
                .addProvider(new CaveToDensityProvider())
                .addProvider(new LakeDepthFacet())
                .addProvider(new LakeDepthProvider())
                .addProvider(new LakeFacet())
                .addProvider(new LakeHeightFacet())
                .addProvider(new LakeHeightProvider())
				.addProvider(new LakeProvider())
                .addProvider(new LavaFacet())
                .addProvider(new org.terasology.mine.lava.LavaProvider())
                .addProvider(new HouseProvider())
                .addRasterizer(new CaveRasterizer())
                .addRasterizer(new HouseRasterizer())
                .addRasterizer(new MineRasterizer())
                .addRasterizer(new LakeRasterizer())
                .addPlugins();
				
    }
}