package org.terasology.mine;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
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
        diamond= CoreRegistry.get(BlockManager.class).getBlock("Core:DiamondOre");

    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {

        LakeFacet lakeFacet = chunkRegion.getFacet(LakeFacet.class);
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);

        for (Vector3i position : chunkRegion.getRegion()) {

            Lake lavaLake = lavaFacet.getNearestLake(position);

            if(lavaFacet.isEnabled() && !lavaLake.isNull() && lake.isInRange(position)) {

                float surfaceHeight = surfaceHeightFacet.getWorld(position.x(), position.z());
                float lakeDepth = lakeDepthFacet.getWorld(position.x(), position.z());
                float lakeHeight = lakeHeightFacet.getWorld(position.x(), position.z());

                if (lavaLake.LakeContains(position) && position.y() <= lavaLake.getWaterHeight() && (position.y() >= lavaLake.getWaterHeight() - lakeDepth ||
                        position.y() > surfaceHeight)) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), lava);
                }

                else if (lavaLake.OuterContains(position) && position.y() <= lavaLake.getWaterHeight() && position.y() >= surfaceHeight) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
                }

                else if (lavaLake.LakeContains(position) && position.y() > lavaLake.getWaterHeight() && position.y() <= lavaLake.getWaterHeight() + lakeHeight) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), air);
                }

                else if(lavalake.LakeContains(position) && position.y() < lavalake.getWaterHeight() && position.y() < surfaceHeight && position.y() < lavalake.getWaterHeight() + lakeHeight){
                    chunk.setBlock(ChunkMath.calcBlockPos(position), diamond);
                    
                }
            }
        }
    }
}