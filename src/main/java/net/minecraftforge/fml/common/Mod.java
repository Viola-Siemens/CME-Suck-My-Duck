/*
 * Minecraft Forge
 * Copyright (c) 2016-2020.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod
{
    String value();
    String modid();
    String name() default "";
    String version() default "";
    String dependencies() default "";
    boolean useMetadata() default false;
    boolean clientSideOnly() default false;
    boolean serverSideOnly() default false;
    String acceptedMinecraftVersions() default "";
    String acceptableRemoteVersions() default "";
    String acceptableSaveVersions() default "";
    String certificateFingerprint() default "";
    String modLanguage() default "java";
    String modLanguageAdapter() default "";
    boolean canBeDeactivated() default false;
    String guiFactory() default "";
    String updateJSON() default "";
    CustomProperty[] customProperties() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface CustomProperty
    {
        /**
         * A key. Should be unique.
         * @return A key
         */
        String k();
        /**
         * A value. Can be anything.
         * @return A value
         */
        String v();
    }
}
