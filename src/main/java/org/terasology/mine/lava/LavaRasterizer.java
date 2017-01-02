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

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.mine.Lake;
import org.terasology.mine.LakeFacet;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
public class LavaRasterizer implements WorldRasterizerPlugin {

    private Block stone;
    private Block air;
    private Block lava;
    private Block diamondore;

    @Override
    public void initialize() {
        lava = CoreRegistry.get(BlockManager.class).getBlock("Core:Lava");
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:Stone");
        air = CoreRegistry.get(BlockManager.class).getBlock("Engine:Air");
        diamondore= CoreRegistry.get(BlockManager.class).getBlock("Core:DiamondOre");

    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {

        LakeFacet lakeFacet = chunkRegion.getFacet(LakeFacet.class);
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);

        /* None of this works - need to declare lavaFacet, lake, lakeDepthFacet and lakeHeightFacet
        for (Vector3i position : chunkRegion.getRegion()) {

            Lake lavaLake = lavaFacet.getNearestLake(position);

            if(lavaFacet.isEnabled() && !lavaLake.isNull() && lake.isInRange(position)) {

                float surfaceHeight = surfaceHeightFacet.getWorld(position.x(), position.z());
                float lakeDepth = lakeDepthFacet.getWorld(position.x(), position.z());
                float lakeHeight = lakeHeightFacet.getWorld(position.x(), position.z());

                if (lavaLake.LakeContains(position) && position.y() <= lavaLake.getWaterHeight() && (position.y() >= lavaLake.getWaterHeight() - lakeDepth
                    || position.y() > surfaceHeight)) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), lava);
                }

                else if (lavaLake.OuterContains(position) && position.y() <= lavaLake.getWaterHeight() && position.y() >= surfaceHeight) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
                }

                else if (lavaLake.LakeContains(position) && position.y() > lavaLake.getWaterHeight() && position.y() <= lavaLake.getWaterHeight() + lakeHeight) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), air);
                }

                else if(lavaLake.LakeContains(position) && position.y() < lavaLake.getWaterHeight() && position.y() < surfaceHeight && position.y() < lavaLake.getWaterHeight() + lakeHeight){
                    chunk.setBlock(ChunkMath.calcBlockPos(position), diamondore);
                }
            }
        }*/
    }
}